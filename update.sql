
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
