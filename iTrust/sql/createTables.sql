/* updated 2014-2-1 */

SET sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

CREATE TABLE users(
	MID                 BIGINT unsigned,
	Password            VARCHAR(200),
	Salt				VARCHAR(200) DEFAULT '',
	openID              VARCHAR(200),
	Role                enum('patient','admin','hcp','uap','er','tester','pha', 'lt') NOT NULL DEFAULT 'admin',
	sQuestion           VARCHAR(100) DEFAULT '', 
	sAnswer             VARCHAR(30) DEFAULT '',
	isDependent			tinyint(1) unsigned NOT NULL default '0',

	PRIMARY KEY (MID),
	UNIQUE (openID)
) ENGINE=MyISAM; 

CREATE TABLE hospitals(
	HospitalID   varchar(10),
	HospitalName varchar(30) NOT NULL,
	Address varchar(30),
	City varchar(15),
	State varchar(2),
	Zip varchar(10),
	
	PRIMARY KEY (hospitalID)
) ENGINE=MyISAM;

CREATE TABLE wards(
	wardID BIGINT unsigned AUTO_INCREMENT primary key,
	inHospital varchar(10) NOT NULL,
	requiredSpecialty varchar(128),
	FOREIGN KEY (InHospital) REFERENCES hospitals (HospitalID)
) ENGINE=MyISAM;

CREATE TABLE personnel(
	MID BIGINT unsigned NOT NULL default 0,
	AMID BIGINT unsigned default NULL,
	role enum('admin','hcp','uap','er','tester','pha', 'lt') NOT NULL default 'admin',
	enabled tinyint(1) unsigned NOT NULL default '0',
	lastName varchar(20) NOT NULL default '',
	firstName varchar(20) NOT NULL default '',
	address1 varchar(30) NOT NULL default '',
	address2 varchar(30) NOT NULL default '',
	city varchar(15) NOT NULL default '',
	state enum('','AK','AL','AR','AZ','CA','CO','CT','DE','DC','FL','GA','HI','IA','ID','IL','IN','KS','KY','LA','MA','MD','ME','MI','MN','MO','MS','MT','NC','ND','NE','NH','NJ','NM','NV','NY','OH','OK','OR','PA','RI','SC','SD','TN','TX','UT','VA','VT','WA','WI','WV','WY') NOT NULL default '',
	zip varchar(10) NOT NULL default '',
	phone varchar(12) NOT NULL default '',
	specialty varchar(40) default NULL,
	email varchar(55)  default '',
	PRIMARY KEY  (MID)
) auto_increment=9000000000 ENGINE=MyISAM;

CREATE TABLE patients(
	MID BIGINT unsigned  auto_increment, 
	lastName varchar(20)  default '', 
	firstName varchar(20)  default '', 
	email varchar(55)  default '', 
	address1 varchar(30)  default '', 
	address2 varchar(30)  default '', 
	city varchar(15)  default '', 
	state enum('AK','AL','AR','AZ','CA','CO','CT','DE','DC','FL','GA','HI','IA','ID','IL','IN','KS','KY','LA','MA','MD','ME','MI','MN','MO','MS','MT','NC','ND','NE','NH','NJ','NM','NV','NY','OH','OK','OR','PA','RI','SC','SD','TN','TX','UT','VA','VT','WA','WI','WV','WY')  default 'AK', 
	zip varchar(10)  default '', 
	phone varchar(12) default '',
	eName varchar(40)  default '', 
	ePhone varchar(12)  default '', 
	iCName varchar(20)  default '', 
	iCAddress1 varchar(30)  default '', 
	iCAddress2 varchar(30)  default '', 
	iCCity varchar(15)  default '', 
	ICState enum('AK','AL','AR','AZ','CA','CO','CT','DE','DC','FL','GA','HI','IA','ID','IL','IN','KS','KY','LA','MA','MD','ME','MI','MN','MO','MS','MT','NC','ND','NE','NH','NJ','NM','NV','NY','OH','OK','OR','PA','RI','SC','SD','TN','TX','UT','VA','VT','WA','WI','WV','WY')  default 'AK', 
	iCZip varchar(10)  default '', 
	iCPhone varchar(12)  default '',
	iCID varchar(20)  default '', 
	DateOfBirth DATE,
	DateOfDeath DATE,
	CauseOfDeath VARCHAR(10) default '',
	MotherMID INTEGER(10) default 0,
	FatherMID INTEGER(10) default 0,
	BloodType VARCHAR(3) default '',
	Ethnicity VARCHAR(20) default '',
	Gender VARCHAR(13) default 'Not Specified',
	TopicalNotes VARCHAR(200) default '',
	CreditCardType VARCHAR(20) default '',
	CreditCardNumber VARCHAR(19) default '',
	DirectionsToHome varchar(512) default '',
	Religion varchar(64) default '',
	Language varchar(32) default '',
	SpiritualPractices varchar(512) default '',
	AlternateName varchar(32) default '',
	DateOfDeactivation DATE default NULL,
	ObstetricsPatient BOOLEAN default false,
	PRIMARY KEY (MID)
) ENGINE=MyISAM;

CREATE TABLE flags(
	FID BIGINT unsigned auto_increment,
	MID BIGINT unsigned NOT NULL default '0',
	pregId BIGINT unsigned NOT NULL default '0',
	flagType enum('High Blood Pressure', 'Advanced Maternal Age', 'Maternal Allergies', 'Low-Lying Placenta',
		'Genetic Miscarriage', 'Abnormal FHR', 'Twins', 'Abnormal Weight Change', 'Negative Blood Type', 'Pregnancy relevant pre-existing conditions'),
	PRIMARY KEY (FID)
) ENGINE=MyISAM;

CREATE TABLE loginfailures(
	ipaddress varchar(128) NOT NULL, 
	failureCount int NOT NULL default 0, 
	lastFailure TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (ipaddress)
) ENGINE=MyISAM;

CREATE TABLE resetpasswordfailures(
	ipaddress varchar(128) NOT NULL, 
	failureCount int NOT NULL default 0, 
	lastFailure TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (ipaddress)
) ENGINE=MyISAM;

CREATE TABLE appointment (
	appt_id				INT UNSIGNED AUTO_INCREMENT primary key,
	doctor_id           BIGINT UNSIGNED NOT NULL,
	patient_id          BIGINT UNSIGNED NOT NULL,
	sched_date          DATETIME NOT NULL,
	appt_type           VARCHAR(30) NOT NULL,
	comment				TEXT
) ENGINE=MyISAM;

CREATE TABLE appointmenttype (
	apptType_id			INT UNSIGNED AUTO_INCREMENT primary key,
	appt_type           VARCHAR(30) NOT NULL,
	duration			INT UNSIGNED NOT NULL,
	price				INT UNSIGNED NOT NULL default '0' /* UC60 */
) ENGINE=MyISAM;

CREATE TABLE appointmentrequests(
	appt_id				INT UNSIGNED AUTO_INCREMENT primary key,
	doctor_id           BIGINT UNSIGNED NOT NULL,
	patient_id          BIGINT UNSIGNED NOT NULL,
	sched_date          DATETIME NOT NULL,
	appt_type           VARCHAR(30) NOT NULL,
	comment				TEXT,
	pending				BOOLEAN NOT NULL,
	accepted			BOOLEAN NOT NULL
) ENGINE=MyISAM;

CREATE TABLE drugreactionoverridecodes(
	Code varchar(5) NOT NULL COMMENT 'Identifier for override reason',
	Description varchar(80) NOT NULL COMMENT 'Description of override reason',
	PRIMARY KEY (Code)
) ENGINE=MyISAM;
	
CREATE TABLE ndcodes(
	Code varchar(10) NOT NULL, 
	Description varchar(100) NOT NULL, 
	PRIMARY KEY  (Code)
) ENGINE=MyISAM;

CREATE TABLE druginteractions(
	FirstDrug varchar(9) NOT NULL,
	SecondDrug varchar(9) NOT NULL,
	Description varchar(100) NOT NULL,
	PRIMARY KEY  (FirstDrug,SecondDrug)
) ENGINE=MyISAM;

CREATE TABLE transactionlog(
	transactionID int(10) unsigned NOT NULL auto_increment, 
	loggedInMID BIGINT unsigned NOT NULL DEFAULT '0', 
	secondaryMID BIGINT unsigned NOT NULL DEFAULT '0', 
	transactionCode int(10) UNSIGNED NOT NULL default '0', 
	timeLogged timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP, 
	addedInfo VARCHAR(255) default '',
	PRIMARY KEY (transactionID)
) ENGINE=MyISAM;

CREATE TABLE hcprelations(
	HCP BIGINT unsigned NOT NULL default '0', 
	UAP BIGINT unsigned NOT NULL default '0',
	PRIMARY KEY (HCP, UAP)
) ENGINE=MyISAM;

CREATE TABLE personalrelations(
	PatientID BIGINT unsigned NOT NULL COMMENT 'MID of the patient',
	RelativeID BIGINT unsigned NOT NULL COMMENT 'MID of the Relative',
	RelativeType VARCHAR( 35 ) NOT NULL COMMENT 'Relation Type'
) ENGINE=MyISAM;

CREATE TABLE representatives(
	representerMID BIGINT unsigned default 0, 
	representeeMID BIGINT unsigned default 0,
	PRIMARY KEY  (representerMID,representeeMID)
) ENGINE=MyISAM;

CREATE TABLE hcpassignedhos(
	hosID VARCHAR(10) NOT NULL, 
	HCPID BIGINT unsigned NOT NULL, 
	PRIMARY KEY (hosID,HCPID)
) ENGINE=MyISAM;

CREATE TABLE declaredhcp(
	PatientID BIGINT unsigned NOT NULL default '0', 
	HCPID BIGINT unsigned NOT NULL default '0', 
	PRIMARY KEY  (PatientID,HCPID)
) ENGINE=MyISAM;

CREATE TABLE billing( /* UC60 */
	billID       int(10) unsigned auto_increment,
	appt_id      INT UNSIGNED,
	PatientID    BIGINT unsigned NOT NULL default '0',
	HCPID        BIGINT unsigned default '0', 
	billTimeS    DATE,
	amt          int,
	status       VARCHAR(20) default '',
	ccHolderName VARCHAR(30),
	billingAddress VARCHAR(120),
	ccType		 VARCHAR(20),
	ccNumber	VARCHAR(40),
	cvv			VARCHAR(4),
	insHolderName VARCHAR(30),
	insID		VARCHAR(20),
	insProviderName	VARCHAR(20),
	insAddress1	VARCHAR(30),
	insAddress2	VARCHAR(30),
	insCity		VARCHAR(20),
	insState	enum('AK','AL','AR','AZ','CA','CO','CT','DE','DC','FL','GA','HI','IA','ID','IL','IN','KS','KY','LA','MA','MD','ME','MI','MN','MO','MS','MT','NC','ND','NE','NH','NJ','NM','NV','NY','OH','OK','OR','PA','RI','SC','SD','TN','TX','UT','VA','VT','WA','WI','WV','WY'),
	insZip		VARCHAR(10),
	insPhone	VARCHAR(12),
	submissions int default 0,
	subTime     datetime,
	isInsurance BOOLEAN default false,
	PRIMARY KEY  (billID),
	FOREIGN KEY  (appt_id) REFERENCES appointment (appt_id),
	FOREIGN KEY  (PatientID) REFERENCES patients (MID),
	FOREIGN KEY  (HCPID) REFERENCES personnel (MID)
) ENGINE=MyISAM;


CREATE TABLE personalallergies(
	PatientID BIGINT unsigned NOT NULL COMMENT 'MID of the Patient',
	Allergy VARCHAR( 50 ) NOT NULL COMMENT 'Description of the allergy'
) ENGINE=MyISAM;


CREATE TABLE allergies(
	ID INT(10) unsigned auto_increment primary key,
	PatientID BIGINT unsigned NOT NULL COMMENT 'MID of the Patient',
	Description VARCHAR( 50 ) NOT NULL COMMENT 'Description of the allergy',
	FirstFound TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Code varchar(10) COMMENT 'NDCode of drug that patient is allergic to'
	/*NEW, Added Code, so that we could pass the NDCode of the drug when adding allergy.*/
) ENGINE=MyISAM;

CREATE TABLE globalvariables (
	Name VARCHAR(20) primary key,
	Value VARCHAR(20)
) ENGINE=MyISAM;

INSERT INTO globalvariables(Name,Value) VALUES ('Timeout', '20');

CREATE TABLE fakeemail(
	ID INT(10) auto_increment primary key,
	ToAddr VARCHAR(100),
	FromAddr VARCHAR(100),
	Subject VARCHAR(500),
	Body VARCHAR(2000),
	AddedDate timestamp NOT NULL default CURRENT_TIMESTAMP
) ENGINE=MyISAM;

CREATE TABLE reportrequests (
	ID INT(10) auto_increment primary key,
    RequesterMID BIGINT unsigned,
    PatientMID BIGINT unsigned,
    ApproverMID BIGINT unsigned,
    RequestedDate datetime,
    ApprovedDate datetime,
    ViewedDate datetime,
    Status varchar(30),
	Comment TEXT
) ENGINE=MyISAM;

CREATE TABLE message (
	message_id          INT UNSIGNED AUTO_INCREMENT,
	parent_msg_id       INT UNSIGNED,
	original_msg_id     INT UNSIGNED,
	from_id             BIGINT UNSIGNED NOT NULL,
	to_id               BIGINT UNSIGNED NOT NULL,
	sent_date           DATETIME NOT NULL,
	message             TEXT,
	subject				TEXT,
	been_read			INT UNSIGNED,
	PRIMARY KEY (message_id)
) ENGINE=MyISAM;




CREATE TABLE remotemonitoringdata (
	id          INT UNSIGNED AUTO_INCREMENT,
	PatientID          BIGINT UNSIGNED NOT NULL,
	systolicBloodPressure int(10) SIGNED default -1,
	diastolicBloodPressure int(10) SIGNED default -1,
	glucoseLevel int(10) SIGNED default -1,
	height float default -1,
	weight float default -1,
	pedometerReading int(10) SIGNED default -1,
	timeLogged timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
	ReporterRole		TEXT,
	ReporterID          BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (id)
) AUTO_INCREMENT=1 ENGINE=MyISAM;

CREATE TABLE remotemonitoringlists (
	PatientMID BIGINT unsigned default 0, 
	HCPMID BIGINT unsigned default 0,
	SystolicBloodPressure BOOLEAN default true,
	DiastolicBloodPressure BOOLEAN default true,
	GlucoseLevel BOOLEAN default true,
	Height BOOLEAN default true,
	Weight BOOLEAN default true,
	PedometerReading BOOLEAN default true,
	PRIMARY KEY  (PatientMID,HCPMID)
) ENGINE=MyISAM;

CREATE TABLE profilephotos (
	MID BIGINT (10) primary key,
	Photo LONGBLOB,
	UpdatedDate timestamp NOT NULL default CURRENT_TIMESTAMP
) ENGINE=MyISAM;



CREATE TABLE WardRooms(
	RoomID BIGINT unsigned AUTO_INCREMENT primary key,
	OccupiedBy BIGINT unsigned default NULL,
	InWard BIGINT unsigned NOT NULL,
	RoomName varchar(128),
	Status varchar(128),
	FOREIGN KEY (InWard) REFERENCES wards (WardID),
	FOREIGN KEY (OccupiedBy) REFERENCES patients (MID)
) ENGINE=MyISAM;

CREATE TABLE HCPAssignedToWard(
	HCP BIGINT unsigned,
	WARD BIGINT unsigned,
	FOREIGN KEY (WARD) REFERENCES wards (WardID),
	FOREIGN KEY (HCP) REFERENCES personnel (MID)
) ENGINE=MyISAM;

CREATE TABLE WardRoomCheckout(
	PID BIGINT unsigned default NULL,
	Reason VARCHAR(120),
	FOREIGN KEY (PID) REFERENCES patients (MID)
) ENGINE=MyISAM;


/*Table for maintaining records release information*/
CREATE TABLE recordsrelease(
	releaseID BIGINT UNSIGNED AUTO_INCREMENT,
	requestDate DATETIME NOT NULL,
	pid	BIGINT UNSIGNED NOT NULL,
	releaseHospitalID VARCHAR(30) NOT NULL,
	recHospitalName VARCHAR(30) NOT NULL,
	recHospitalAddress VARCHAR(120) NOT NULL,
	docFirstName VARCHAR(20) NOT NULL,
	docLastName VARCHAR(20) NOT NULL,
	docPhone VARCHAR(12) NOT NULL,
	docEmail VARCHAR(100) NOT NULL,
	justification VARCHAR(120),
	status INT NOT NULL,
	PRIMARY KEY(releaseID)
) ENGINE=MyISAM;
    
CREATE TABLE reviews(
    mid  BIGINT UNSIGNED NOT NULL,
    pid  BIGINT UNSIGNED NOT NULL,
    reviewdate DATETIME NOT NULL,
    descriptivereview VARCHAR(500),
    rating INT UNSIGNED NOT NULL,
    title VARCHAR(200)
)ENGINE=MyISAM;

CREATE TABLE IF NOT EXISTS zipcodes (
  `zip` varchar(5) NOT NULL default '',
  `state` char(2) NOT NULL default '',
  `latitude` varchar(10) NOT NULL default '',
  `longitude` varchar(10) NOT NULL default '',
  `city` varchar(50) default NULL,
  `full_state` varchar(50) default NULL,
  PRIMARY KEY `zip` (`zip`)
) ENGINE=MyISAM;

CREATE TABLE officeVisit
(
	visitID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	patientMID BIGINT(20) UNSIGNED NOT NULL,
	visitDate DATETIME NOT NULL,
	locationID VARCHAR(10) NOT NULL,
	apptTypeID INT(10) UNSIGNED NOT NULL,
	notes TEXT,
	sendBill BOOLEAN default TRUE,
	height FLOAT,
	length FLOAT,
	weight FLOAT,
	head_circumference FLOAT,
	blood_pressure VARCHAR(7),
	hdl INT(2),
	triglyceride INT(3),
	ldl INT(3),
	household_smoking_status TINYINT(1),
	patient_smoking_status TINYINT(1),
	PRIMARY KEY (visitID),
	FOREIGN KEY (patientMID) REFERENCES patients(MID),
	FOREIGN KEY (locationID) REFERENCES hospitals(HospitalID),
	FOREIGN KEY (apptTypeID) REFERENCES appointmenttype(apptType_id)
)  ENGINE=MyISAM;

CREATE TABLE labProcedure (
	labProcedureID 		BIGINT(20)		UNSIGNED NOT NULL AUTO_INCREMENT,
	labTechnicianID		BIGINT(20)		UNSIGNED NOT NULL,
	hcpMID				BIGINT(20)		UNSIGNED NOT NULL,
	officeVisitID 		BIGINT(20)		UNSIGNED NOT NULL,
	labProcedureCode	VARCHAR(7), 
	priority			INT				UNSIGNED,
	isRestricted		BOOLEAN,
	status 				BIGINT(20)		UNSIGNED NOT NULL,
	commentary 			TEXT,
	results 			TEXT,
	updatedDate 		TIMESTAMP 		NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	confidenceIntervalLower	INT			UNSIGNED, /* Lower number in the confidence interval */
	confidenceIntervalUpper INT			UNSIGNED, /* Higher number in the confidence interval */
	PRIMARY KEY (labProcedureID),
	FOREIGN KEY	(labTechnicianID)		REFERENCES personnel(MID),
	FOREIGN KEY	(hcpMID)				REFERENCES personnel(MID),
	FOREIGN KEY	(officeVisitID)			REFERENCES officeVisit(visitID)
) ENGINE=MyISAM;

CREATE TABLE prescription (
	patientMID BIGINT(20) UNSIGNED NOT NULL,
	drugCode varchar(10) NOT NULL,
	startDate DATE,
	endDate DATE,
	officeVisitId BIGINT(20) UNSIGNED NOT NULL,
	id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	instructions VARCHAR(100) NOT NULL DEFAULT 'No Instructions Provided',
	dosage BIGINT(20) UNSIGNED NOT NULL DEFAULT 1,
	hcpMID BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (officeVisitId) REFERENCES officeVisit(visitID),
	FOREIGN KEY (patientMID) REFERENCES patients(MID),
	FOREIGN KEY (drugCode) REFERENCES ndcodes(Code)
) ENGINE=MyISAM;

CREATE TABLE IF NOT EXISTS cptCode (
	Code 				VARCHAR(5), 
	name		 		VARCHAR(30) 	NOT NULL, 
	PRIMARY KEY  (Code)
) ENGINE=MyISAM;

CREATE TABLE immunization (
	id 						BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	visitId 				BIGINT(20)		UNSIGNED NOT NULL,
	cptCode 				varchar(5) 		NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (visitId) 	REFERENCES officeVisit(visitID),
	FOREIGN KEY (cptCode) 	REFERENCES cptCode(code)
) ENGINE=MyISAM;

CREATE TABLE icdCode
(
	code VARCHAR(8),
	name VARCHAR(100) NOT NULL,
	is_chronic TINYINT(1) NOT NULL DEFAULT '0',
	PRIMARY KEY (code)
) ENGINE=MyISAM;

CREATE TABLE loincCode
(
	code VARCHAR(7) NOT NULL,
	component VARCHAR(100) NOT NULL,
	kind_of_property VARCHAR(100) NOT NULL,
	time_aspect VARCHAR(100),
	system VARCHAR(100),
	scale_type VARCHAR(100),
	method_type VARCHAR(100),
	PRIMARY KEY (code)
) ENGINE=MyISAM;

CREATE TABLE diagnosis
(
	id BIGINT(20) UNSIGNED AUTO_INCREMENT,
	visitId BIGINT(20) UNSIGNED NOT NULL,
	icdCode VARCHAR(8) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (visitId) REFERENCES officeVisit(visitID),
	FOREIGN KEY (icdCode) REFERENCES icdCode(code)
) ENGINE=MyISAM;

CREATE TABLE medicalProcedure
(
	id 						BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	visitId 				BIGINT(20)		UNSIGNED NOT NULL,
	cptCode 				varchar(5) 		NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (visitId) 	REFERENCES officeVisit(visitID),
	FOREIGN KEY (cptCode) 	REFERENCES cptCode(code)
) ENGINE=MyISAM;

CREATE TABLE fitnessData
(
	id 						BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	pid 				BIGINT		UNSIGNED NOT NULL,
	calories		INT NOT NULL,
	steps			INT NOT NULL,
	distance		FLOAT NOT NULL,
	floors			INT NOT NULL,
	minsSed			INT NOT NULL,
	minsLA			INT NOT NULL,
	minsFA			INT NOT NULL,
	minsVA			INT NOT NULL,
	activeCals		INT NOT NULL,
	activeHours 	INT NOT NULL,
	hrLow			INT NOT NULL,
	hrHigh			INT NOT NULL,
	hrAvg			INT NOT NULL,
	uvExposure		INT NOT NULL,
	fitnessDate		DATE NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (pid) 	REFERENCES patients(MID),
	CONSTRAINT pid_date UNIQUE (pid, fitnessDate)
) ENGINE=MyISAM;

CREATE TABLE obstetricsData
(
	id 				BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	pid 			BIGINT		UNSIGNED,
	initDate		DATE,
	lmp				DATE,
	edd				DATE,
	weeksPregnant	VARCHAR(20) default '',
	concepYear		VARCHAR(20) default '',
	totalWeeks		VARCHAR(20) default '',
	hrsLabor		FLOAT default NULL,
	weightGain		VARCHAR(20) default '',
	deliveryType	VARCHAR(100) default '',
	multiplePregnancy	BOOLEAN NOT NULL default false,
	babyCount		VARCHAR(20) default '',
	current			BOOLEAN NOT NULL default true,
	rhFlag			BOOLEAN NOT NULL default false,
	hpMiscarriage	BOOLEAN NOT NULL default false,
	PRIMARY KEY (id),
	FOREIGN KEY (pid) 	REFERENCES patients(MID),
	CONSTRAINT pid_date UNIQUE (pid, initDate)
) ENGINE=MyISAM;

CREATE TABLE obstetricsOfficeVisitData
(
	id 				BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	pid				BIGINT	UNSIGNED,
	initId			BIGINT(20) UNSIGNED,
	visitDate		DATE,
	weeksPregnant	VARCHAR(20) default "",
	weight			VARCHAR(20) default "",
	bp				VARCHAR(20) default "",
	fhr				VARCHAR(20) default "",
	multiPregnancy	BOOLEAN default FALSE,
	numBabies		VARCHAR(20) default "",
	lowPlacenta		BOOLEAN default FALSE,
	PRIMARY KEY (id),
	FOREIGN KEY (pid) 	REFERENCES patients(MID)
) ENGINE=MyISAM;

CREATE TABLE ultrasoundData
(
	id 				BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	pid				BIGINT	UNSIGNED,
	dateCreated		DATE,
	picPath			VARCHAR(100) default "",
	img				mediumblob	default null,
	ovId			BIGINT(20)	UNSIGNED,
	PRIMARY KEY (id),
	FOREIGN KEY (pid) 	REFERENCES patients(MID),
	FOREIGN KEY (ovId) REFERENCES obstetricsOfficeVisitData(id)
) ENGINE=MyISAM;

CREATE TABLE fetusData
(
	id 				BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	ovId			BIGINT(20) NOT NULL,
	crl				VARCHAR(20) default "",
	bpd				VARCHAR(20) default "",
	hc				VARCHAR(20) default "",
	fl				VARCHAR(20) default "",
	ofd				VARCHAR(20) default "",
	ac				VARCHAR(20) default "",
	hl				VARCHAR(20) default "",
	efw				VARCHAR(20) default "",
	multiNum		INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (ovId) 	REFERENCES obstetricsOfficeVisitData(id)
) ENGINE=MyISAM;

CREATE TABLE childbirthData
(
	id 				BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	pid				BIGINT(20) NOT NULL,
	initId			BIGINT(20) NOT NULL,
	visitDate		DATE NOT NULL,
	ER				BOOLEAN default FALSE,
	deliveryType	VARCHAR(100) default "",
	amtEpidural		VARCHAR(20) default "",
	amtMagnesium	VARCHAR(20) default "",
	amtNitrous		VARCHAR(20) default "",
	amtPethidine	VARCHAR(20) default "",
	amtPitocin		VARCHAR(20) default "",
	amtRH			VARCHAR(20) default "",
	epidural		BOOLEAN default FALSE,
	magnesium		BOOLEAN default FALSE,
	nitrous			BOOLEAN default FALSE,
	pethidine		BOOLEAN default FALSE,
	pitocin			BOOLEAN default FALSE,
	PRIMARY KEY (id),
	FOREIGN KEY (pid) 	REFERENCES patients(MID)
) ENGINE=MyISAM;

CREATE TABLE babyData
(
	id 				BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	childbirthId	BIGINT(20) NOT NULL,
	name			VARCHAR(20) default "",
	birthDate		VARCHAR(20) default "",
	birthTime		VARCHAR(10) default "",
	estimatedDate	BOOLEAN default FALSE,
	sex				INT NOT NULL,
	deliveryType	VARCHAR(100) default "",
	multiNum		INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (childbirthId) 	REFERENCES childbirthData(id)
) ENGINE=MyISAM;

CREATE TABLE colorData
(
	id 				BIGINT(20) 		UNSIGNED AUTO_INCREMENT,
	pid				BIGINT(20) NOT NULL,
	lmb				VARCHAR(40) default "rgba(247, 247, 247, 0.59)",
	mtc				VARCHAR(40) default "#fff",
	mbc				VARCHAR(40) default "#333",
	ftc				VARCHAR(40) default "#E04646",
	nbc				VARCHAR(40) default "#222",
	ntc				VARCHAR(40) default "#999",
	fbc				VARCHAR(40) default "rgba(247, 247, 247, 0.59)",
	spb				VARCHAR(40) default "#D6D6D6",
	tb1				VARCHAR(40) default "#f1f1f1",
	tb2				VARCHAR(40) default "#E7E7E7",
	thb				VARCHAR(40) default "rgb(75, 75, 75)",
	tht				VARCHAR(40) default "white",
	etc				VARCHAR(40) default "#cc0000",
	wtc				VARCHAR(40) default "rgb(255, 255, 255)",
	PRIMARY KEY (id),
	FOREIGN KEY (pid) 	REFERENCES patients(MID)
) ENGINE=MyISAM;
