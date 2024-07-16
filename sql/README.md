[# Introduction

# SQL Quries

###### Table Setup (DDL)
```sql
CREATE TABLE cd.members(
 	memid INT PRIMARY KEY,
	surname VARCHAR (100),
  	firstname VARCHAR(100),
  	address VARCHAR (300),
  	zipcode INT,
   	telephone VARCHAR (20),
  	recommendedby INT,
  	joindate TIMESTAMP,
  	FOREIGN KEY (recommendedby) REFERENCES memid(cd.members) 	
);



CREATE TABLE cd.facilities (
		facid INT PRIMARY KEY,
  		name VARCHAR(100),
  		membercost NUMERIC,
  		guestcost NUMERIC,
  		initialoutlay NUMERIC,
  		monthlymaintenance NUMERIC
);
  

CREATE TABLE cd.bookings(
 	 	bookid INT PRIMARY KEY,
  		facid INT,
  		memid INT,
  		starttime TIMESTAMP, 
  		slots INT ,
  		FOREIGN KEY (facid) REFERENCES facid(cd.facilities),
  		FOREIGN KEY (memid) REFERENCES memid(cd.members)
);
```


###### Question 1: Insert a new facility in the table
```sql
INSERT INTO cd.facilities VALUES (9, "Spa", 20, 30, 10000, 800);
```

###### Questions 2: Insert a new facility with an automatic generated id

```sql
INSERT INTO cd.facilities VALUES ((SELECT COUNT(facid) FROM facilities), "Spa", 20, 30, 10000, 800);
```

###### Questions 3:Update the initial outlay value inside the facilitu Table

```sql
UPDATE cd.facilities
SET initialoutlay = 10000
WHERE initialoutlay = 8000;
```

###### Questions 4: Change a certain price bad of the price of another data in the table

```sql
UPDATE cd.facilities
SET membercost = (SELECT membercost + membercost * 0.1 FROM cd.facilities WHERE name = "Tennis Court 2")
WHERE name = "Tennis Court 2";
```

###### Questions 5: Delete every row from the bookings table

```sql
DELETE FROM cd.bookings;
```

###### Questions 6: Delete all the rows where the condition is matched

```sql
DELETE FROM cd.members
WHERE memid = 37;
```

###### Questions 7: Produce a list of facilities that charge a fee to members, and that fee is less than 1/50th of the monthly maintenance cost

```sql
SELECT facid, name, membercost, monthlymaintenance FROM cd.facilities
Where membercost < monthlymaintenance / 50 AND membercost > 0;
```

###### Questions 8: Produce a list of all facilities with the word 'Tennis' in their name

```sql
SELECT * FROM cd.facilities 
WHERE name LIKE '%Tennis%'; 
```

###### Questions 9: Retrieve the details of facilities with ID 1 and 5 without using the OR operator

```sql
SELECT * FROM cd.facilities
WHERE facid IN (1,5);
```

###### Questions 10: Produce a list of members who joined after the start of September 2012

```sql
SELECT memid, surname, firstname, joindate 
FROM cd.members
WHERE joindate > '2012-09-01 00:00:00';
```

###### Questions 11: Produce a combined list of all surnames and all facility names

```sql
SELECT name
FROM cd.facilities
UNION 
SELECT surname
FROM cd.members;
```

###### Questions 12: Produce a list of the start times for bookings by members named 'David Farrell'

```sql
SELECT cd.bookings.starttime 
FROM cd.bookings
INNER JOIN cd.members
ON cd.members.memid =  cd.bookings.memid
WHERE cd.members.firstname = 'David' AND cd.members.surname = 'Farrell';
```
###### Questions 13: produce a list of the start times for bookings for tennis courts, for the date '2012-09-21'

```sql
SELECT starttime, name
FROM cd.bookings
INNER JOIN cd.facilities
ON cd.bookings.facid = cd.facilities.facid
WHERE name IN ('Tennis Court 1', 'Tennis Court 2') AND (starttime > '2012-09-21 00:00:00' AND starttime <= '2012-09-21 23:59:59');
```

###### Questions 14: Output a list of all members, including the individual who recommended them
```sql
SELECT mems.firstname AS memfname, mems.surname AS memsname, recs.firstname AS recfname, recs.surname AS recsname
FROM 
cd.members mems
left outer JOIN cd.members recs
ON recs.memid = mems.recommendedby
ORDER BY memsname, memfname;    
```

###### Questions 15: Output a list of all members who have recommended another member
```sql
SELECT DISTINCT recs.firstname AS firstname, recs.surname AS surname
FROM 
cd.members mems
INNER JOIN cd.members recs
ON recs.memid = mems.recommendedby
ORDER BY surname, firstname; 
```

###### Questions 16: Output a list of all members, including the individual who recommended them without using any joins

```sql
SELECT DISTINCT mems.firstname || ' ' ||  mems.surname AS member,
	(SELECT recs.firstname || ' ' || recs.surname AS recommender 
		FROM cd.members recs 
		WHERE recs.memid = mems.recommendedby
	)
	FROM 
		cd.members mems
ORDER BY member;  
```

###### Questions 17: Produce a count of the number of recommendations each member has made. Order by member ID.

```sql
SELECT recommendedby, count(*)
FROM cd.members 
WHERE recommendedby IS NOT NULL
GROUP BY recommendedby
ORDER BY recommendedby;
```

###### Questions 18: Produce a list of the total number of slots booked per facility

```sql
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;
```

###### Questions 19: Produce a list of the total number of slots booked per facility in the month of September 2012

```sql
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE starttime >= '2012-09-01' AND starttime <= '2012-09-30'
GROUP BY facid
ORDER BY "Total Slots";
```

###### Questions 20: Produce a list of the total number of slots booked per facility per month in the year of 2012

```sql
SELECT facid, EXTRACT(MONTH FROM starttime) AS month, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE EXTRACT(YEAR FROM starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month;
```

###### Questions 21: Find the total number of members (including guests) who have made at least one booking

```sql
SELECT COUNT(DISTINCT memid)
FROM cd.bookings;
```

###### Questions 22: Produce a list of each member name, id, and their first booking after September 1st 2012. Order by member ID

```sql
SELECT cd.members.surname, cd.members.firstname, cd.members.memid, MIN(starttime) as starttime
FROM cd.bookings
INNER JOIN cd.members
ON cd.members.memid = cd.bookings.memid
WHERE starttime >= '2012-09-01'
GROUP BY cd.members.surname, cd.members.firstname, cd.members.memid
ORDER BY cd.members.memid;
```

###### Questions 23: Produce a list of member names, with each row containing the total member count

```sql
SELECT COUNT(*) OVER(), firstname, surname
FROM cd.members
ORDER BY joindate;
```

###### Questions 24: Produce a monotonically increasing numbered list of members (including guests), ordered by their date of joining

```sql
SELECT ROW_NUMBER() OVER(ORDER BY joindate) AS row_number, firstname, surname
FROM cd.members
ORDER BY joindate;
```

###### Questions 25: Output the facility id that has the highest number of slots booked

```sql
SELECT facid, total FROM (
	SELECT facid, sum(slots) total, rank() OVER (ORDER BY sum(slots) DESC) rank
        	FROM cd.bookings
		GROUP BY facid
	) AS ranked
	WHERE rank = 1
```

###### Questions 26: Output the names of all members, formatted as 'Surname, Firstname'

```sql
SELECT CONCAT(surname,', ', firstname) AS name
FROM cd.members;
```

###### Questions 27: find all the telephone numbers that contain parentheses, returning the member ID and telephone number sorted by member ID.

```sql
SELECT memid, telephone
FROM cd.members
WHERE POSITION('(' IN telephone) IS NOT NULL AND POSITION(')' IN telephone) IS NOT NULL;
```

###### Questions 28:  Produce a count of how many members you have whose surname starts with each letter of the alphabet.

```sql
SELECT substr (mems.surname,1,1) as letter, count(*) AS count 
FROM cd.members mems
GROUP BY letter
ORDER BY letter
```
