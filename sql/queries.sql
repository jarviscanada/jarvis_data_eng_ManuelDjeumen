-- Table Creation
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






-- SQL Tables Updates

--Question 1
INSERT INTO cd.facilities VALUES (9, "Spa", 20, 30, 10000, 800);


--Question 2
INSERT INTO cd.facilities VALUES ((SELECT COUNT(facid) FROM facilities), "Spa", 20, 30, 10000, 800);

-- Question 3
UPDATE cd.facilities
SET initialoutlay = 10000
WHERE initialoutlay = 8000;

-- Question 4
UPDATE cd.facilities
SET membercost = (SELECT membercost + membercost * 0.1 FROM cd.facilities WHERE name = "Tennis Court 2")
WHERE name = "Tennis Court 2";


--Question 5
DELETE FROM cd.bookings;

--Question 6
DELETE FROM cd.members
WHERE memid = 37;

--Question 7
SELECT facid, name, membercost, monthlymaintenance FROM cd.facilities
Where membercost < monthlymaintenance / 50 AND membercost > 0;

--Question 8
SELECT * FROM cd.facilities 
WHERE name LIKE '%Tennis%'; 


--Question 9
SELECT * FROM cd.facilities
WHERE facid IN (1,5);

--Question 10
SELECT memid, surname, firstname, joindate 
FROM cd.members
WHERE joindate > '2012-09-01 00:00:00';

--Question 11
SELECT name
FROM cd.facilities
UNION 
SELECT surname
FROM cd.members;

--Question 12
SELECT cd.bookings.starttime 
FROM cd.bookings
INNER JOIN cd.members
ON cd.members.memid =  cd.bookings.memid
WHERE cd.members.firstname = 'David' AND cd.members.surname = 'Farrell';


--Question 13
SELECT starttime, name
FROM cd.bookings
INNER JOIN cd.facilities
ON cd.bookings.facid = cd.facilities.facid
WHERE name IN ('Tennis Court 1', 'Tennis Court 2') AND (starttime > '2012-09-21 00:00:00' AND starttime <= '2012-09-21 23:59:59');


--Question 14
SELECT mems.firstname AS memfname, mems.surname AS memsname, recs.firstname AS recfname, recs.surname AS recsname
FROM 
cd.members mems
left outer JOIN cd.members recs
ON recs.memid = mems.recommendedby
ORDER BY memsname, memfname;    


-- Question 15
SELECT DISTINCT recs.firstname AS firstname, recs.surname AS surname
FROM 
cd.members mems
INNER JOIN cd.members recs
ON recs.memid = mems.recommendedby
ORDER BY surname, firstname; 

-- Question 16
SELECT DISTINCT mems.firstname || ' ' ||  mems.surname AS member,
	(SELECT recs.firstname || ' ' || recs.surname AS recommender 
		FROM cd.members recs 
		WHERE recs.memid = mems.recommendedby
	)
	FROM 
		cd.members mems
ORDER BY member;  


-- Question 17
SELECT recommendedby, count(*)
FROM cd.members 
WHERE recommendedby IS NOT NULL
GROUP BY recommendedby
ORDER BY recommendedby;

-- Question 18
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;

-- Question 19
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE starttime >= '2012-09-01' AND starttime <= '2012-09-30'
GROUP BY facid
ORDER BY "Total Slots";

-- Question 20
SELECT facid, EXTRACT(MONTH FROM starttime) AS month, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE EXTRACT(YEAR FROM starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month;

-- Question 21
SELECT COUNT(DISTINCT memid)
FROM cd.bookings;

-- Question 22
SELECT cd.members.surname, cd.members.firstname, cd.members.memid, MIN(starttime) as starttime
FROM cd.bookings
INNER JOIN cd.members
ON cd.members.memid = cd.bookings.memid
WHERE starttime >= '2012-09-01'
GROUP BY cd.members.surname, cd.members.firstname, cd.members.memid
ORDER BY cd.members.memid;

-- Question 23
SELECT COUNT(*) OVER(), firstname, surname
FROM cd.members
ORDER BY joindate;

-- Question 24
SELECT ROW_NUMBER() OVER(ORDER BY joindate) AS row_number, firstname, surname
FROM cd.members
ORDER BY joindate;

-- Question 25
SELECT facid, total FROM (
	SELECT facid, sum(slots) total, rank() OVER (ORDER BY sum(slots) DESC) rank
        	FROM cd.bookings
		GROUP BY facid
	) AS ranked
	WHERE rank = 1



-- Question 26
SELECT CONCAT(surname,', ', firstname) AS name
FROM cd.members;

-- Question 27
SELECT memid, telephone
FROM cd.members
WHERE POSITION('(' IN telephone) IS NOT NULL AND POSITION(')' IN telephone) IS NOT NULL;

-- Question 28
SELECT substr (mems.surname,1,1) as letter, count(*) AS count 
FROM cd.members mems
GROUP BY letter
ORDER BY letter