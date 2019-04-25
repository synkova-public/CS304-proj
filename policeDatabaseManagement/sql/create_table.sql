create table Officer
( Address char(30) not null,
    ID Integer not null, 
    Name char(25) not null, 
    Hired char(10) not null,
    PRIMARY KEY (ID) );

create table Record
( ID char(7) not null,
    Description char(100) not null, 
    Case_date char(10) not null,
    PRIMARY KEY (ID) );

create table People
( PhoneNumber char(15) not null,
    SIN integer not null,
    Name char(25) not null,
    Address char(25) not null,
    Age integer not null,
    PRIMARY KEY (SIN) );

create table CriminalType
( TypeName char(20) not null,
    PRIMARY KEY (TypeName) );

create table Court 
( RecordID char(7) not null,
    CourtID integer not null,
    Judge char(25) not null,
    Description char(300) not null,
    Result char(10) not null,
    Hearing char(10) not null,  
    PRIMARY KEY (CourtID),
    foreign key (RecordID) references Record on delete cascade);

create table CriminalRecordType 
( RecordID char(7) not null, 
    TypeName char(20) not null, 
    PRIMARY KEY (RecordID), 
    foreign key (RecordID) references Record on delete cascade, 
    foreign key (TypeName) references CriminalType on delete cascade);

create table PeopleInvolved
( RecordID    char(7) not null,
    SIN        INTEGER not null,
    Primary key(RecordID, SIN),
    foreign key (RecordID) references Record(ID) on delete cascade,
    foreign key (SIN) references people(SIN) on delete cascade);
    
    --changed the capitalization on recordId -> RecordID and Sin -> SIN

create table Charge
( OfficerID integer not null,
    RecordID char(7) not null, 
    PRIMARY KEY (OfficerID, RecordID),
    foreign key (OfficerID) references Officer(ID) on delete cascade,
    foreign key (RecordID) references Record(ID) on delete cascade);
    
    --changed the capitalization on officerId -> OfficerID and recordID -> RecordID
    
create table Victim (
SIN integer not null,
primary key (SIN),
foreign key (SIN) references People(SIN) on delete cascade);

create table Suspect (
SIN integer not null,
primary key (SIN),
foreign key (SIN) references People(SIN) on delete cascade);

