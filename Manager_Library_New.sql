use master
go

DROP DATABASE IF EXISTS Manager_Library
CREATE DATABASE Manager_Library;
GO

use Manager_Library
go

CREATE TABLE ACCOUNT
(
USERNAME VARCHAR(20) primary key,
[PASSWORD] VARCHAR(max),
[STATUS] BIT DEFAULT 1
);
GO

Insert into ACCOUNT VALUES ('admin','$2a$10$1utgxX5/tN7tPUU06gAgXOM3OisJAZ8KkykTaUrIjm3GqeBY8ywgm',1)
CREATE TABLE BOOKS(
BOOK_ID VARCHAR(20) PRIMARY KEY,  --MA SACH
TITLE VARCHAR(MAX) NOT NULL,	--ten sach
AUTHOR NVARCHAR(MAX) NOT NULL,	-- tac gia
ID_CATEGORY VARCHAR(50) NOT NULL,		--loai sach
QUANTITY INT  NOT NULL	check (QUANTITY >= 0),--so luong tong sach
IMG VARCHAR(50)
);
GO

--=================================================
CREATE TABLE CATEGORY(
CATEGORY_ID VARCHAR(50) PRIMARY KEY, --
CATEGORY_NAME NVARCHAR(MAX) NOT NULL 
);
GO

--=================================================
CREATE TABLE PEOPLE(
ID VARCHAR(20) PRIMARY KEY,
[NAME] NVARCHAR(50) NOT NULL,
PHONENUMBER VARCHAR(11) NOT NULL,
EMAIL NVARCHAR(MAX) NOT NULL,
CLASS_NAME NVARCHAR(50),
)
GO

--=================================================
create TABLE CALLCARD (  
ID INT PRIMARY KEY IDENTITY,
ID_PEOPLE VARCHAR(20) NOT NULL,
DATE_BORROWED DATE,
DATE_DUE DATE,
QUANTITY_BORROWED INT, --SO LUONG MUON
QUANTITY_DUE INT DEFAULT 0,  ---SO LUONG DA TRA
OVERDUE_FINES INT DEFAULT 0,
[STATUS] BIT DEFAULT 0
);
GO

--=========================================================
CREATE TABLE BOOKS_DETAIL(
ID_CALLCARD INT,
ID_BOOK VARCHAR(20),
QUANTITY INT,
QUANTITY_DUE INT DEFAULT 0  ---SO LUONG DA TRA
CONSTRAINT PK_ID_BOOKID PRIMARY KEY (ID_CALLCARD,ID_BOOK)
);
GO

--=========================================================

ALTER TABLE CALLCARD
ADD CONSTRAINT FK_CALLCARD_PEOPLE
FOREIGN KEY (ID_PEOPLE) REFERENCES PEOPLE(ID)

ALTER TABLE BOOKS
ADD CONSTRAINT FK_BOOKS_CATEGORY
FOREIGN KEY (ID_CATEGORY) REFERENCES CATEGORY(CATEGORY_ID)

ALTER TABLE BOOKS_DETAIL
ADD CONSTRAINT FK_BOOKS_BOOKDETAIL
FOREIGN KEY (ID_BOOK) REFERENCES BOOKS(BOOK_ID)

ALTER TABLE BOOKS_DETAIL
ADD CONSTRAINT FK_CALLCARD_BOOKDETAIL
FOREIGN KEY (ID_CALLCARD) REFERENCES CALLCARD(ID)


--==============================
INSERT INTO CATEGORY
VALUES
('COM','COMIC'),
('REFER','REFERENCE BOOKS'),
('DIC','DICTIONARY')
go

--=================================================================================
INSERT INTO BOOKS
VALUES ('CN01','DETECTIVE CONAN 1 ','Aoyama Gosho','COM',50,'conan1.jpg'),
('CN02','DETECTIVE CONAN 2','Aoyama Gosho','COM',35,'conan2.jpg'),
('CN03','DETECTIVE CONAN 3','Aoyama Gosho','COM',35,'conan3.jpg'),
('DRM01','DORAEMON 1','Fujiko Fujio','COM',50,'doraemon1.jpg'),
('DRM02','DORAEMON 2','Fujiko Fujio','COM',50,'doraemon2.jpg'),
('DRM03','DORAEMON 3','Fujiko Fujio','COM',45,'doraemon3.jpg')
go 

--=================================================================================
INSERT INTO PEOPLE
VALUES('Student2000101', N'Quỳnh Anh', '0394035290', 'quynhanh2606201@gmail.com', 'C1908I1'),
	('Student2000102', N'HYHY', '0833210100', 'nhathuy2606201@gmail.com', 'C1908I1'),
	('Student2000103', N'Đình Ánh', '0100000001', 'dinhanh_baby@gmail.com', 'C1908I1'),
	('Student2000104', N'Ngọc Trân', '0765716380', 'tranmini0401@gmail.com', 'C1908I1'),
	('Student2000105', N'Ngọc Tú', '0795637211', 'ngoctu_cute@gmail.com', 'C1808G1'),
	('Student2000106', N'Linh Tinh', '0121212121', 'linhtinh123@gmail.com', 'C1908I1'),
	('Student2000107', N'Hoàng Ngọc', '0131313131', 'Hoangngoc456@gmail.com', 'C1908I1'),
	('Student2000108', N'Vy Vy', '0141414141', 'vyvy_goodgirl@gmail.com', 'C1908I1'),
	('Student2000109', N'Ny Ny', '0151515151', 'nyny_badgirl@gmail.com', 'C1908I1'),
	('Student2000110', N'Na Na', '0161616161', 'nana_baby123@gmail.com', 'C1808G1')
go
--=================================================================================
INSERT INTO CALLCARD
VALUES
	   ('Student2000101', '2020-10-27','2020-11-02',2,0,0,1),
	   ('Student2000102', '2020-10-28','2020-11-01',3,0,0,0),
	   ('Student2000103', '2020-10-30','2020-11-05',5,0,0,0),
	   ('Student2000104', '2020-10-31','2020-11-06',5,0,0,0),
	   ('Student2000105', '2020-11-01','2020-11-06',2,0,0,0),
	   ('Student2000106', '2020-11-02','2020-11-07',3,0,0,0),
	   ('Student2000107', '2020-10-29','2020-11-04',1,0,0,0),
	   ('Student2000108', '2020-10-30','2020-11-03',1,0,0,0),
	   ('Student2000109', '2020-10-31','2020-11-03',1,0,0,0),
	   ('Student2000110', '2020-10-28','2020-11-02',1,0,0,0)
go
--=================================================================================
INSERT INTO BOOKS_DETAIL
VALUES
		(1,'CN01',2,0),
		(2,'DRM01',1,0),
		(2,'DRM02',2,0),
		(3,'DRM03',5,0),
		(4,'CN01',1,0),
		(4,'CN02',3,0),
		(4,'CN03',1,0),
		(5,'CN03',1,0),
		(5,'CN02',1,0),
		(6,'CN01',1,0),
		(6,'CN02',1,0),
		(6,'DRM01',1,0),
		(7,'CN01',1,0),
		(8,'CN01',1,0),
		(9,'CN02',1,0),
		(10,'CN03',1,0)
go
--=================================================================================

create proc selectBooks
as
begin
	select ROW_NUMBER() over (order by TITLE) [NO] ,* 
	from BOOKS join CATEGORY on BOOKS.ID_CATEGORY = CATEGORY.CATEGORY_ID
end
go

--=================================================================================
create proc selectCallCard
as
begin
	select ROW_NUMBER() over (order by CALLCARD.ID) [NO], *
	from CALLCARD join PEOPLE on CALLCARD.ID_PEOPLE = PEOPLE.ID
	where STATUS = 0
end
go
--=================================================================================
create proc selectCallCardFalse
as
begin
	select ROW_NUMBER() over (order by CALLCARD.ID) [NO], *
	from CALLCARD join PEOPLE on CALLCARD.ID_PEOPLE = PEOPLE.ID
	where STATUS = 1
end
go
--=================================================================================

create proc selectBook_WithID
@id_book varchar(20)
as
begin
	select * from BOOKS where BOOK_ID = @id_book
end
go

--=================================================================================
create PROC UpdateBook
@bookid VARCHAR(20), @title VARCHAR(max), @author NVARCHAR(max), @idcategory VARCHAR(50), @quantity INT, @img VARCHAR(50)
AS
BEGIN
	UPDATE BOOKS SET TITLE = @title, AUTHOR = @author, ID_CATEGORY = @idcategory, QUANTITY = @quantity ,IMG= img WHERE BOOK_ID = @bookid
END
GO

--=================================================================================
create PROC InsertBook
@bookid VARCHAR(20), @title VARCHAR(max), @author NVARCHAR(max), @idcategory VARCHAR(50), @quantity INT, @img VARCHAR(50)
AS
BEGIN
	INSERT INTO BOOKS VALUES(@bookid, @title, @author, @idcategory, @quantity, @img)
END
GO

--=================================================================================
create proc selectCategory_WithID
@categoryid varchar(50)
as
begin
	select * from CATEGORY where CATEGORY_ID = @categoryid
end
go

--=================================================================================
create proc selectCategory_WithCateName
@catename varchar(max)
as
begin
	select * from CATEGORY where CATEGORY_NAME = @catename
end
go
--=================================================================================
CREATE PROC InsertCategory
@CateId VARCHAR(50), @categoryName NVARCHAR(MAX)
AS
BEGIN
	INSERT INTO CATEGORY VALUES(@CateId, @categoryName)
END
GO

--=================================================================================
create proc DeleteBook
@bookid VARCHAR(20)
as
begin
	Delete from BOOKS where BOOK_ID = @bookid
end
go
--=================================================================================
create proc RemoveCallCard
@bookCallCard VARCHAR(20)
as
begin
	Update CALLCARD set [STATUS] = 0 where ID = @bookCallCard
end
go
--=================================================================================

create proc selectCallCard_People
@ID int
as
begin
	select ROW_NUMBER() over (order by CALLCARD.ID) [NO], *
	from CALLCARD join PEOPLE on CALLCARD.ID_PEOPLE = PEOPLE.ID
				  join BOOKS_DETAIL on CALLCARD.ID = BOOKS_DETAIL.ID_CALLCARD
	where CALLCARD.ID = @ID
end
go
--=================================================================================

create proc selectBook_Detail_CallCard_Books
@id int
as
begin
	select ROW_NUMBER() over (order by CALLCARD.ID) [NO], *
	from BOOKS_DETAIL join CALLCARD on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID
					  join BOOKS on BOOKS.BOOK_ID = BOOKS_DETAIL.ID_BOOK
	where CALLCARD.ID = @id
end
go

--=================================================================================

create proc selectBook_People
@id int
as
begin
	select ROW_NUMBER() over (order by CALLCARD.ID) [NO], *
	from BOOKS_DETAIL join CALLCARD on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID
					  join BOOKS on BOOKS.BOOK_ID = BOOKS_DETAIL.ID_BOOK
					  join PEOPLE on PEOPLE.ID=CALLCARD.ID_PEOPLE
	where CALLCARD.ID = @id
end
go
--=================================================================================
CREATE proc DueList
as
begin
	select ROW_NUMBER() over (order by CALLCARD.ID) [NO],ID_CALLCARD, ID_PEOPLE, [NAME],CLASS_NAME, QUANTITY_BORROWED,DATE_DUE from CALLCARD  join PEOPLE on CALLCARD.ID_PEOPLE = PEOPLE.ID
							Join BOOKS_DETAIL on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID 
							where DATEDIFF(Day, GetDate() , DATE_DUE) <=2  and DATEDIFF(Day, GetDate() , DATE_DUE) >=0
							group by CALLCARD.ID, ID_PEOPLE, [NAME],CLASS_NAME, QUANTITY_BORROWED,DATE_DUE,BOOKS_DETAIL.ID_CALLCARD
							ORDER BY DATE_DUE ASC
end
go
--=================================================================================
create proc bookBorrowedest
as
begin
	select top 6 sum(BOOKS_DETAIL.QUANTITY) as NUM,  BOOK_ID ,AUTHOR,TITLE
	from BOOKS_DETAIL join CALLCARD on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID
					  join BOOKS on BOOKS.BOOK_ID = BOOKS_DETAIL.ID_BOOK				
	group by BOOK_ID ,AUTHOR,TITLE
	order by NUM DESC
end
go

--=================================================================================
create proc OverDueList
as
begin
	select ROW_NUMBER() over (order by CALLCARD.ID) [NO],OVERDUE_FINES = 2000 * ( DATEDIFF(Day, DATE_DUE,GetDate())) * QUANTITY_BORROWED , QUANTITY_BORROWED, CALLCARD.DATE_DUE, CALLCARD.ID, ID_PEOPLE , [NAME] , CLASS_NAME from CALLCARD 
							join PEOPLE on CALLCARD.ID_PEOPLE = PEOPLE.ID
							Join BOOKS_DETAIL on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID 
							where DATEDIFF(Day, DATE_DUE,GetDate()) > = 1
							group by CALLCARD.OVERDUE_FINES, CALLCARD.DATE_DUE, CALLCARD.ID, ID_PEOPLE , [NAME] , CLASS_NAME, QUANTITY_BORROWED,  ID_PEOPLE
							ORDER BY DATE_DUE ASC
end
go
--=================================================================================
create proc DetailOverDue
@idCallCard int
as
begin
	select ROW_NUMBER() over (order by ID_CALLCARD) [NO], ID_CALLCARD, BOOK_ID, BOOKS_DETAIL.QUANTITY, BOOKS_DETAIL.QUANTITY_DUE, TITLE, AUTHOR, ID_CATEGORY, IMG, ID_PEOPLE, DATE_BORROWED, DATE_DUE, TOTAL = 2000 * ( DATEDIFF(Day, DATE_DUE,GetDate())) * BOOKS_DETAIL.QUANTITY
	from BOOKS_DETAIL join BOOKS on BOOKS_DETAIL.ID_BOOK = BOOKS.BOOK_ID
					  join CALLCARD on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID
	where ID_CALLCARD = @idCallCard and DATEDIFF(Day, DATE_DUE,GetDate()) > 0
end
go


--=================================================================================
create proc SumTotal_DetailOverDue
@idCallCard int
as
begin
	select ROW_NUMBER() over (order by ID_CALLCARD) [NO],TOTAL = sum(2000 * ( DATEDIFF(Day, DATE_DUE,GetDate())) * BOOKS_DETAIL.QUANTITY)
	from BOOKS_DETAIL join BOOKS on BOOKS_DETAIL.ID_BOOK = BOOKS.BOOK_ID
					  join CALLCARD on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID
	where ID_CALLCARD = @idCallCard and DATEDIFF(Day, DATE_DUE,GetDate()) > 0
	group by BOOKS_DETAIL.ID_CALLCARD
end
go
--=================================================================================

create proc PayBooks
@QUANTITY_DUE INT, @ID INT,@ID_PEOPLE VARCHAR(20), @BOOK_ID VARCHAR(20)
as
begin
	
	declare @quantity int =(select QUANTITY from BOOKS_DETAIL where ID_CALLCARD=@ID and ID_BOOK =@BOOK_ID)
	declare @quantityDue int =(select QUANTITY_DUE from BOOKS_DETAIL where ID_CALLCARD=@ID and ID_BOOK =@BOOK_ID)
	declare @checkquantity int =(select QUANTITY_DUE from CALLCARD where ID=@ID)
	declare @checkquantity_borrowed int =(select QUANTITY_BORROWED from CALLCARD where ID=@ID)
		declare @day int = (select DATEDIFF(Day, DATE_DUE, GetDate()) from CALLCARD where ID=@ID)

	if(@quantity >= (@QUANTITY_DUE+@quantityDue) and  @checkquantity <= @checkquantity_borrowed and @day > 0)
	begin
		UPDATE BOOKS_DETAIL SET QUANTITY_DUE +=@QUANTITY_DUE where ID_BOOK=@BOOK_ID and ID_CALLCARD=@ID
		UPDATE BOOKS SET QUANTITY += @QUANTITY_DUE where BOOK_ID = @BOOK_ID
		UPDATE CALLCARD SET QUANTITY_DUE += @QUANTITY_DUE where ID=@ID
				update CALLCARD set	OVERDUE_FINES += (2000 * DATEDIFF(Day, DATE_DUE,GetDate())  * @QUANTITY_DUE )where ID=@ID and ID_PEOPLE =@ID_PEOPLE
		return 1;
	end

	if(@quantity >= (@QUANTITY_DUE+@quantityDue) and  @checkquantity <= @checkquantity_borrowed and @day <= 0)
	begin
		UPDATE BOOKS_DETAIL SET QUANTITY_DUE +=@QUANTITY_DUE where ID_BOOK=@BOOK_ID and ID_CALLCARD=@ID
		UPDATE BOOKS SET QUANTITY += @QUANTITY_DUE where BOOK_ID = @BOOK_ID
		UPDATE CALLCARD SET QUANTITY_DUE += @QUANTITY_DUE where ID=@ID
				update CALLCARD set	OVERDUE_FINES += 0 where ID=@ID and ID_PEOPLE =@ID_PEOPLE
		return 2;
	end

	if(@quantity < (@QUANTITY_DUE+@quantityDue) and @checkquantity <= @checkquantity_borrowed and @day <= 0)
	begin
	  --update CALLCARD set	OVERDUE_FINES = 0 where ID=@ID and ID_PEOPLE =@ID_PEOPLE
		ROLLBACK;
	end
		  	return 0;
end
go
--=================================================================================
create proc BookIsBorrowed
@dayStart date, @dayEnd date
as
begin
	select sum(BOOKS_DETAIL.QUANTITY) as BOOK_IS_BORROWED
	from BOOKS_DETAIL join CALLCARD on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID
					  join BOOKS on BOOKS_DETAIL.ID_BOOK = BOOKS.BOOK_ID
	where DATE_BORROWED  between @dayStart and @dayEnd

end
go

--=================================================================================
create proc TotalOfGuests
@dayStart date, @dayEnd date
as
begin
	select count(*) as GUESTS
	from
	(select ID_PEOPLE
	from CALLCARD
	where DATE_BORROWED  between @dayStart and @dayEnd
	group by ID_PEOPLE) as aaa
end
go
--=================================================================================
create proc  TotalCallCard
@dayStart date, @dayEnd date
as
begin
	select count (*) as TOTAL_CALLCARD
	from CALLCARD
	where DATE_BORROWED  between @dayStart and @dayEnd
end
go
--=================================================================================
create proc  TotalGuestsBorrowing
@dayStart date, @dayEnd date
as
begin
	select count(*) as GUESTS
	from
	(select ID_PEOPLE
	from CALLCARD
	where QUANTITY_DUE < QUANTITY_BORROWED and DATE_BORROWED  between @dayStart and @dayEnd
	group by ID_PEOPLE) as aaa
end
go
--=================================================================================
create proc  TotalCardOverdue
as
begin
	select count(*)
	from
		(select ROW_NUMBER() over (order by CALLCARD.ID) [NO],OVERDUE_FINES = 2000 * ( DATEDIFF(Day, DATE_DUE,GetDate())) * QUANTITY_BORROWED , QUANTITY_BORROWED, CALLCARD.DATE_DUE, CALLCARD.ID, ID_PEOPLE , [NAME] , CLASS_NAME 
		from CALLCARD join PEOPLE on CALLCARD.ID_PEOPLE = PEOPLE.ID
					  Join BOOKS_DETAIL on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID 
		where DATEDIFF(Day, DATE_DUE,GetDate()) > = 1
		group by CALLCARD.OVERDUE_FINES, CALLCARD.DATE_DUE, CALLCARD.ID, ID_PEOPLE , [NAME] , CLASS_NAME, QUANTITY_BORROWED, ID_PEOPLE ) as bbb
end
go
--=================================================================================
create proc PayList
as
begin
	select ROW_NUMBER() over (order by CALLCARD.ID) [NO], OVERDUE_FINES, QUANTITY_BORROWED, CALLCARD.DATE_DUE, CALLCARD.ID, ID_PEOPLE , [NAME] , CLASS_NAME 
		from CALLCARD 
			  join PEOPLE on CALLCARD.ID_PEOPLE = PEOPLE.ID
			  Join BOOKS_DETAIL on BOOKS_DETAIL.ID_CALLCARD = CALLCARD.ID 	  
		where CALLCARD.QUANTITY_BORROWED = CALLCARD.QUANTITY_DUE
		group by CALLCARD.OVERDUE_FINES, CALLCARD.DATE_DUE, CALLCARD.ID, ID_PEOPLE , [NAME] , CLASS_NAME, QUANTITY_BORROWED, ID_PEOPLE 
end
go
--=================================================================================
CREATE PROC selectInfoCallcardOfPeople
@ID_PEOPLE VARCHAR(20)
AS
BEGIN
SELECT BOOKS_DETAIL.ID_BOOK, CALLCARD.ID_PEOPLE, BOOKS_DETAIL.QUANTITY, BOOKS_DETAIL.ID_CALLCARD
FROM BOOKS_DETAIL JOIN CALLCARD ON ID_CALLCARD = ID
WHERE ID_PEOPLE = @ID_PEOPLE AND BOOKS_DETAIL.QUANTITY > BOOKS_DETAIL.QUANTITY_DUE
END
GO

--=================================================================================
create proc selectAdmin
@user VARCHAR(20)
as
begin
 	select *
	from Account where USERNAME = @user 
end
go
--=================================================================================
create proc selectPasword
@user VARCHAR(20)
as
begin 
select [PASSWORD] from Account where USERNAME =@user
end
go
--=================================================================================
create proc selectwithPass
@pass VARCHAR(MAX)
as
begin
 	select *
	from Account where [PASSWORD] = @pass
end
go
--=================================================================================
create proc changePass
@user VARCHAR(20) ,@pass VARCHAR(MAX)
as
begin
 UPDATE ACCOUNT SET [PASSWORD]= @pass where USERNAME= @user
end
go