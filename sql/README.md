
# Introduction

This project was about SQL. It was meant for us to train ourselves and exercise about Databases and some of the commands needed for a queries. We had to implement queries based on specifications about a certain database and then show the output of those one live. 

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


###### Question 1:
```sql
INSERT INTO cd.facilities VALUES (9, "Spa", 20, 30, 10000, 800);
```

###### Questions 2: Lorem ipsum...

```sql
INSERT INTO cd.facilities VALUES ((SELECT COUNT(facid) FROM facilities), "Spa", 20, 30, 10000, 800);
```

###### Questions 3: Lorem ipsum...

```sql
UPDATE cd.facilities
SET initialoutlay = 10000
WHERE initialoutlay = 8000;
```

###### Questions 4: Lorem ipsum...

```sql
UPDATE cd.facilities
SET membercost = (SELECT membercost + membercost * 0.1 FROM cd.facilities WHERE name = "Tennis Court 2")
WHERE name = "Tennis Court 2";
```

###### Questions 5: Lorem ipsum...

```sql
DELETE FROM cd.bookings;
```

###### Questions 6: Lorem ipsum...

```sql
DELETE FROM cd.members
WHERE memid = 37;
```

###### Questions 7: Lorem ipsum...

```sql
SELECT facid, name, membercost, monthlymaintenance FROM cd.facilities
Where membercost < monthlymaintenance / 50 AND membercost > 0;
```

###### Questions 8: Lorem ipsum...

```sql
SELECT * FROM cd.facilities 
WHERE name LIKE '%Tennis%'; 
```

###### Questions 9: Lorem ipsum...

```sql
SELECT * FROM cd.facilities
WHERE facid IN (1,5);
```

###### Questions 10: Lorem ipsum...

```sql
SELECT memid, surname, firstname, joindate 
FROM cd.members
WHERE joindate > '2012-09-01 00:00:00';
```

###### Questions 11: Lorem ipsum...

```sql
SELECT name
FROM cd.facilities
UNION 
SELECT surname
FROM cd.members;
```

###### Questions 12: Lorem ipsum...

```sql
SELECT cd.bookings.starttime 
FROM cd.bookings
INNER JOIN cd.members
ON cd.members.memid =  cd.bookings.memid
WHERE cd.members.firstname = 'David' AND cd.members.surname = 'Farrell';
```
###### Questions 13: Lorem ipsum...

```sql
SELECT starttime, name
FROM cd.bookings
INNER JOIN cd.facilities
ON cd.bookings.facid = cd.facilities.facid
WHERE name IN ('Tennis Court 1', 'Tennis Court 2') AND (starttime > '2012-09-21 00:00:00' AND starttime <= '2012-09-21 23:59:59');
```

###### Questions 14: Lorem ipsum...

```sql
SELECT mems.firstname AS memfname, mems.surname AS memsname, recs.firstname AS recfname, recs.surname AS recsname
FROM 
cd.members mems
left outer JOIN cd.members recs
ON recs.memid = mems.recommendedby
ORDER BY memsname, memfname;    
```

###### Questions 15: Lorem ipsum...

```sql
SELECT DISTINCT recs.firstname AS firstname, recs.surname AS surname
FROM 
cd.members mems
INNER JOIN cd.members recs
ON recs.memid = mems.recommendedby
ORDER BY surname, firstname; 
```

###### Questions 16: Lorem ipsum...

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

###### Questions 17: Lorem ipsum...

```sql
SELECT recommendedby, count(*)
FROM cd.members 
WHERE recommendedby IS NOT NULL
GROUP BY recommendedby
ORDER BY recommendedby;
```

###### Questions 18: Lorem ipsum...

```sql
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;
```

###### Questions 19: Lorem ipsum...

```sql
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE starttime >= '2012-09-01' AND starttime <= '2012-09-30'
GROUP BY facid
ORDER BY "Total Slots";
```

###### Questions 20: Lorem ipsum...

```sql
SELECT facid, EXTRACT(MONTH FROM starttime) AS month, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE EXTRACT(YEAR FROM starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month;
```

###### Questions 21: Lorem ipsum...

```sql
SELECT COUNT(DISTINCT memid)
FROM cd.bookings;
```

###### Questions 22: Lorem ipsum...

```sql
SELECT cd.members.surname, cd.members.firstname, cd.members.memid, MIN(starttime) as starttime
FROM cd.bookings
INNER JOIN cd.members
ON cd.members.memid = cd.bookings.memid
WHERE starttime >= '2012-09-01'
GROUP BY cd.members.surname, cd.members.firstname, cd.members.memid
ORDER BY cd.members.memid;
```

###### Questions 23: Lorem ipsum...

```sql
SELECT COUNT(*) OVER(), firstname, surname
FROM cd.members
ORDER BY joindate;
```

###### Questions 24: Lorem ipsum...

```sql
SELECT ROW_NUMBER() OVER(ORDER BY joindate) AS row_number, firstname, surname
FROM cd.members
ORDER BY joindate;
```

###### Questions 25: Lorem ipsum...

```sql
SELECT facid, total FROM (
	SELECT facid, sum(slots) total, rank() OVER (ORDER BY sum(slots) DESC) rank
        	FROM cd.bookings
		GROUP BY facid
	) AS ranked
	WHERE rank = 1
```

###### Questions 26: Lorem ipsum...

```sql
SELECT CONCAT(surname,', ', firstname) AS name
FROM cd.members;
```

###### Questions 27: Lorem ipsum...

```sql
SELECT memid, telephone
FROM cd.members
WHERE POSITION('(' IN telephone) IS NOT NULL AND POSITION(')' IN telephone) IS NOT NULL;
```

###### Questions 28: Lorem ipsum...

```sql
SELECT substr (mems.surname,1,1) as letter, count(*) AS count 
FROM cd.members mems
GROUP BY letter
ORDER BY letter
```
