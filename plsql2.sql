#create table student2(id number(10),name varchar2(100));
#Explicit Cursor
Declare

Cursor mycursor is select * from student1;
Cursor mycursor2 is select id from student2;

eid student1.id%type;
ename student1.name%type;
nid student2.id%type;
count1 number(10) := 1;
	Begin
	open mycursor;
	open mycursor2;

	loop
		count1 :=1;
		fetch mycursor into eid,ename;
		exit when mycursor%notfound;
		loop
			fetch mycursor2 into nid;
			exit when mycursor2%notfound;
			if(eid=nid) then
				count1:=0;
				exit;
			end if;
		end loop;
		if(count1=1) then
			dbms_output.put_line('executed');
			insert into student2 values(eid,ename);
		else
			dbms_output.put_line('already present');		
		end if;		
	end loop;								
	End;
/		