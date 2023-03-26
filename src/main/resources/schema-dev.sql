CREATE TABLE IF NOT EXISTS Account(acc_id BIGINT PRIMARY KEY,
                                   hand_bal numeric,
                                   acc_bal numeric,
                                   total_bal numeric);

CREATE TABLE IF NOT EXISTS Users(user_id BIGINT PRIMARY KEY,
                                 email  varchar(256) NOT NULL UNIQUE ,
                                 password varchar(1024),
                                 acc_id BIGINT unique ,
                                 CONSTRAINT FK_USR_ID FOREIGN KEY (acc_id) REFERENCES Account(acc_id));

