create table library2(id number(10) primary key,book_name varchar(20),detail varchar(50));

create sequence sq2 start with 3;	

create table library_audit(id number(10) primary key,book_name varchar(20) not null,detail varchar(50) default null,changedate date,libraryid int,foreign key(libraryid) references library2(id));


insert into library2 values(sq2.nextval,'oop','Programming lnag book');

create or replace trigger inserttrig After insert on library2 for each row
Begin
		insert into library_audit values(:new.id,:new.book_name,:new.detail,sysdate,:new.id);	
end;
/


Update library2 set book_name='CN' where id=3;

create or replace trigger AfterLibraryUpdate After Update of book_name on library2 for each row
Begin
		Update library_audit set book_name= :old.book_name where id = :old.id;
end;
/

create or replace trigger AfterLibrary_audit2delete After Delete on library_audit for each row
Begin
		Delete from library2 where id = :old.id;
end;
/









