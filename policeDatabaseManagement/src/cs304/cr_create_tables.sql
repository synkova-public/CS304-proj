create table Officer (
  ID integer not null,
  Address char(30) not null,
  Name char(20) not null,
  Hired char(11) not null,
  primary key(ID));

create table Record (
  ID char(7) not null,
  Description char(100) not null,
  CaseDate char(11) not null,
  primary key (ID));

create table Charge (
  OfficerID integer not null,
  recordID char(7) not null,
  primary key (officerID, recordID),
  foreign key (officerID) references Officer);

create table People(
  PhoneNumber char(15) not null,
  SIN integer not null,
  Name char(20) not null,
  Address char(20) not null,
  primary key (SIN));

create table Court (
  RecordID char(7) not null,        
  CourtID integer not null,
  Judge char(20) not null,
  Description char(300) not null,
  Result char(300) not null,
  Hearing char(11) not null,
  primary key (CourtID),
  foreign key (RecordID) references Record);

create table CriminalType (
  TypeName char(10) not null,
  Frequency integer not null,
  primary key (TypeName));

 
create table CriminalRecordType (
  RecordID char(7) not null,
  TypeName char(10) not null,
  primary key (RecordID, TypeName),
  foreign key (RecordID) references Record,
  foreign key (TypeName) references CriminalType);

create table PeopleInvolved (
  RecordID char(7) not null,
  SIN integer not null,
  primary key(RecordID, SIN),
  foreign key (RecordID) references Record,
  foreign key (SIN) references People); 


commit;

