

DROP TABLE if exists daily_cardio;
DROP TABLE if exists client_calories;
DROP TABLE if exists individual_session;
DROP TABLE if exists sessions;
DROP TABLE if exists client;



CREATE TABLE client (
    client_id serial,
    username varchar(255) NOT NULL UNIQUE,
    user_password varchar(255) NOT NULL UNIQUE,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    gender boolean NOT NULL,
    age DECIMAL(5,2) NOT NULL,
    weight_lbs DECIMAL(5,2) NOT NULL,
    height DECIMAL(5,2) NOT NULL,
    salt_string varchar(255),
    CONSTRAINT pk_client_client_id PRIMARY KEY (client_id)
);


CREATE TABLE client_calories (client_id integer , 
                              todays_date date, 
                              calories_consumed int NOT NULL, 
                              calories_needed int NOT NULL,
                              FOREIGN KEY (client_id) REFERENCES client(client_id));



CREATE TABLE daily_cardio (
    cardio_id serial,
    client_id integer NOT NULL,
    cardio_date date NOT NULL,
    duration integer NOT NULL,
    modality varchar(255),
    calories_burnt integer NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(client_id));
      
CREATE TABLE sessions(
    session_id serial,
    client_id integer NOT NULL,
    cost Decimal(6,2) NOT NULL,
    amount_bought integer NOT NULL,
    amount_used integer,
    date_bought date NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    CONSTRAINT pk_sessions_session_id PRIMARY KEY (session_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id));
    
CREATE TABLE individual_session(
    individual_session_id serial,
    session_id integer NOT NULL,
    date_bought date NOT NULL,
    date_redeemed date,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    cost Decimal(6,2) NOT NULL,
    CONSTRAINT pk_individual_session_individual_session_id PRIMARY KEY (individual_session_id),
    FOREIGN KEY (session_id) REFERENCES sessions (session_id)
    );


//new stuff to do calorie tracking

BEGIN TRANSACTION;

DROP TABLE if exists client_calories
DROP TABLE if exists meal_log
DROP TABLE if exists forum

ALTER TABLE client
ADD daily_cals_needed int;

ALTER TABLE client
ADD goal_weight int;


CREATE TABLE meal_log (       
      meal_log_id SERIAL,
      client_id integer , 
      date DATE,
      meal_number integer,
      meal VARCHAR(255), 
      protein integer,
      carbs integer,
      fat integer,
      calories_consumed int NOT NULL, 
      FOREIGN KEY (client_id) REFERENCES client(client_id)
);

CREATE TABLE forum(
      forum_id SERIAL,
      client_id integer,
      date DATE,
      content VARCHAR(255),
      FOREIGN KEY (client_id) REFERENCES client(client_id)   
      
);

CREATE TABLE goals(
     goal_id SERIAL,
     client_id integer,
     goal VARCHAR(255) NOT NULL,
     accomplish_date date,
     achieved boolean,  
     FOREIGN KEY (client_id) REFERENCES client(client_id)
)

SELECT meal_number FROM meal_log WHERE client_id = 2 AND date = '2017-12-29' ORDER BY meal_number ASC 

SELECT * FROM meal_log where client_id = 2 AND date = '2017-12-26';
DELETE FROM meal_log
ROLLBACK;

COMMIT;