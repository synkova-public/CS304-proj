create table Officer
( ID integer not null,
Address char(30) not null,
Name char(20) not null, 
Hired char(11) not null,
PRIMARY KEY (ID) );

create table Record
( ID char(7) not null,
Description char(100) not null, 
Case_date char(11) not null,
PRIMARY KEY (ID) );

create table Charge
( officerID integer not null,
recordID integer not null, 
PRIMARY KEY (officerID, recordID),
foreign key (officerID) references Officer );

create table People
( PhoneNumber char(15) not null,
SIN integer not null,
Name char(20) not null,
Address char(20) not null,
PRIMARY KEY (SIN) );

create table Court 
( RecordID integer not null,
CourtID integer not null,
Judge char(20) not null,
Description char(300) not null,
Result char(300) not null,
Hearing char(11) not null,
PRIMARY KEY (CourtID),
foreign key (RecordID) references Record );

create table CriminalType
( TypeName char(10) not null,
Frequency integer not null,
PRIMARY KEY (TypeName, Frequency) );

 
create table CriminalRecordType 
( RecordID integer not null, 
TypeName char(10) not null, 
PRIMARY KEY (RecordID, TypeName), 
foreign key (RecordID) references Record, 
foreign key (TypeName) references CriminalType );

create table peopleInvolved (
    //connect record id with SIN
    recordID    INTEGER foreign key references record(ID),
    Sin        INTEGER foreign key references people(SIN),
    Primary key(record id, sin));


commit;
