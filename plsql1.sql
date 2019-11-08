#create table borrower(Roll_No number(10) primary key,Name varchar2(100),DateOfIssue Date,NameOfBook varchar2(100),status varchar2(100)); 
#create table Fine(Roll_No number(10) primary key,DateOfReturn Date,Amount varchar2(100));
#insert into borrower values(1,'harshad',TO_DATE('2019/10/15','yyyy/mm/dd'),'java','N');	
#insert into borrower values(4,'mahesh',TO_DATE('2019/11/5','yyyy/mm/dd'),'CPP','N');
#select DateOfIssue into issueDate from borrower where Roll_No = Roll_No and NameOfBook=Name;
#NoOfDays := (sysdate - issueDate);
Declare
	
	Roll number(10) := &Roll ;
	Name varchar2(100) :='&Name';
	fine number(10);
	issueDate Date;
	NoOfDays number(10);
	checkstatus varchar2(100);

	myexcep EXCEPTION;
	Begin

		select DateOfIssue into issueDate from borrower where Roll_No = Roll;
		NoOfDays := (sysdate - issueDate);
		dbms_output.put_line('No Of Days are:'||NoOfDays);

		select status into checkstatus from borrower where Roll_No=Roll;

		dbms_output.put_line('No Of Days are:'||checkstatus);
		
	if(checkstatus <>'Y') then		
		if (NoOfDays<=0) then
		   raise myexcep;
		elsif (NoOfDays > 14 and NoOfDays <31) then
			fine :=NoOfDays*5;   
			insert into Fine values(Roll,sysdate,fine);
			update borrower set status='Y' where Roll_No=Roll;
		elsif (NoOfDays > 30) then		
			fine :=((NoOfDays-30)*50)+150;   
			insert into Fine values(Roll,sysdate,fine);
			update borrower set status='Y' where Roll_No=Roll;
		else
			fine :=0;
			update borrower set status='Y' where Roll_No=Roll;
		end if;	
	else
		dbms_output.put_line('Already Submitted ');
	end if;		

		EXCEPTION
		when myexcep then
			dbms_output.put_line('Invalid Operation');			
	End;

	/