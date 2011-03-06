create table t_book(
	c_id int primary key,
	c_name varchar(60) not null,
	c_author varchar(60),
	c_translator varchar(30),
	c_pages int not null,
	c_code varchar(20),
	c_book_concern varchar(30),
	c_py varchar(30),
	c_fpy varchar(255),
	c_fileName varchar(45),
	c_read_count int default 0,
	c_download_count int default 0,
	c_state int default 0,
	c_book_date date,
	c_cover_img varchar(45),
	c_swf varchar(45),
	c_desc text,
	c_created_date datetime,
	c_created_by varchar(30)
)


create table t_manager(
	c_id int primary key auto_increment,
	c_login_name varchar(20) not null unique,
	c_password varchar(20) not null default 'root',
	c_name varchar(10),
	c_contact varchar(30),
	c_ext_int int,
	c_ext_str varchar(50)
);

create table t_type(
	c_id int primary key auto_increment,
	c_name varchar(30) not null unique,
	c_desc text
);

create table t_book_type(
	c_book_id int not null references t_book(c_id),
	c_type_id int not null references t_type(c_id),
	primary key(c_book_id,c_type_id)
);

create table t_book_hilo(
	c_hi int
);

create table t_lms_log(
	c_id bigint primary key auto_increment,
	c_login_name  varchar(20) not null,
	c_user_id int not null,
	c_role	int not null,
	c_ip varchar(60) not null,
	c_handle varchar(200) not null,
	c_date datetime not null
);

insert into t_manager values(null,'admin','admin','admin',null,null,null);
insert into t_type values(null,'默认分类','默认分类');
insert into t_book_hilo values(0);
----------------
--
--
----------------