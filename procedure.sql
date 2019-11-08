#create table stud_marks(roll number(10) primary key,name varchar2(100),total_marks number(10));
#create table Result(roll number(10)primary key,name varchar2(100),class varchar2(100));
create or replace procedure proc_Grade (roll in number,marks in number,class out varchar2) is
	Begin

		if (marks<=1500 and marks>=990) then
			class := 'Distinction';
		elsif(marks<=989 and marks>=900)then
			class := 'First Class';
		elsif(marks<=899 and marks>=825)then		
			class := 'Higher Second Class';
		else
			dbms_output.put_line('Invalid');	
		end if;
			
	End;

	/	


insert into stud_marks values(1,'harshad',1000);
insert into stud_marks values(2,'omkar',900);	


Declare

cursor mycursor is select * from stud_marks;
proll stud_marks.roll%type;
pname stud_marks.name%type;
pmarks stud_marks.total_marks%type;
pclass Result.class%type;
	Begin
		open mycursor;
		loop
			fetch mycursor into proll,pname,pmarks;
			exit when mycursor%notfound;
			proc_Grade(proll,pmarks,pclass);
			insert into Result values(proll,pname,pclass);
		end loop;		
	end;

	/