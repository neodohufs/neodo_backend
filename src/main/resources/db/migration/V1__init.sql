create table users (
                       id bigint not null auto_increment,
                       email varchar(255) not null,
                       password varchar(255) not null,
                       username varchar(255) not null,
                       primary key (id)
) engine=InnoDB;

create table topics (
                        id bigint not null auto_increment,
                        speech_board_id bigint,
                        topic varchar(255),
                        primary key (id)
) engine=InnoDB;

create table speech_coachings (
                                  created_at TIMESTAMP null,
                                  id bigint not null auto_increment,
                                  topic_id bigint,
                                  record varchar(255),
                                  title varchar(255),
                                  primary key (id)
) engine=InnoDB;

create table speech_coaching_feedbacks (
                                           score integer not null,
                                           id bigint not null auto_increment,
                                           speech_coaching_id bigint,
                                           conclusion TEXT,
                                           modified_stt TEXT,
                                           original_stt TEXT,
                                           primary key (id)
) engine=InnoDB;

create table speech_boards (
                               created_at TIMESTAMP null,
                               deadline bigint,
                               id bigint not null auto_increment,
                               user_id bigint,
                               record varchar(255),
                               title varchar(255),
                               atmosphere enum ('FORMAL','INFORMAL'),
                               audience enum ('EXPERT','GENERAL','KNOWLEDGEABLE'),
                               purpose enum ('DEBATE','INFORMATIVE','PERSUASIVE','REPORTING'),
                               scale enum ('LARGE','MEDIUM','SMALL'),
                               primary key (id)
) engine=InnoDB;

create table speech_board_feedbacks (
                                        score integer not null,
                                        id bigint not null auto_increment,
                                        speech_board_id bigint,
                                        conclusion TEXT,
                                        modified_stt TEXT,
                                        original_stt TEXT,
                                        primary key (id)
) engine=InnoDB;

create table scripts (
                         created_at TIMESTAMP null,
                         deadline bigint,
                         id bigint not null auto_increment,
                         user_id bigint,
                         title varchar(255),
                         atmosphere enum ('FORMAL','INFORMAL'),
                         audience enum ('EXPERT','GENERAL','KNOWLEDGEABLE'),
                         edited_script TEXT,
                         purpose enum ('DEBATE','INFORMATIVE','PERSUASIVE','REPORTING'),
                         scale enum ('LARGE','MEDIUM','SMALL'),
                         script TEXT,
                         primary key (id)
) engine=InnoDB;

create table script_feedbacks (
                                  id bigint not null auto_increment,
                                  script_id bigint,
                                  feedback TEXT,
                                  primary key (id)
) engine=InnoDB;
