Create Database HanaShop
Use HanaShop
GO
ALTER TABLE [sysdiagrams] SET (LOCK_ESCALATION = TABLE)
GO

CREATE TABLE [Tbl_CartDetail] (
  [ID] int  NOT NULL,
  [userID] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [foodID] int  NULL,
  [quantity] int  NULL,
  [price] float(53)  NULL,
  CONSTRAINT [PK_Tbl_CartDetail] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
GO
ALTER TABLE [Tbl_CartDetail] SET (LOCK_ESCALATION = TABLE)
GO

CREATE TABLE [Tbl_Categorys] (
  [categoryID] int  NOT NULL,
  [categoryName] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  CONSTRAINT [PK_Tbl_Categorys] PRIMARY KEY CLUSTERED ([categoryID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
GO
ALTER TABLE [Tbl_Categorys] SET (LOCK_ESCALATION = TABLE)
GO

CREATE TABLE [Tbl_Foods] (
  [foodID] int  NOT NULL,
  [foodName] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [createDate] date  NULL,
  [categoryID] int  NULL,
  [image] varchar(max) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [quantity] int  NULL,
  [price] float(53)  NULL,
  [status] bit  NULL,
  CONSTRAINT [PK__Tbl_Food__77EAEA19F5A76D70] PRIMARY KEY CLUSTERED ([foodID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
GO
ALTER TABLE [Tbl_Foods] SET (LOCK_ESCALATION = TABLE)
GO

CREATE TABLE [Tbl_Images] (
  [imageID] int  NOT NULL,
  [imageName] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [originalFormart] nvarchar(5) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [imageFile] varbinary(max)  NOT NULL,
  CONSTRAINT [PK__Tbl_Imag__336E9B75EA056A52] PRIMARY KEY CLUSTERED ([imageID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
GO
ALTER TABLE [Tbl_Images] SET (LOCK_ESCALATION = TABLE)
GO

CREATE TABLE [Tbl_OrderDetail] (
  [ID] int  NOT NULL,
  [orderID] int  NULL,
  [foodID] int  NULL,
  [foodName] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [quantity] int  NULL,
  [price] float(53)  NULL,
  CONSTRAINT [PK_Tbl_OrderDetail] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
GO
ALTER TABLE [Tbl_OrderDetail] SET (LOCK_ESCALATION = TABLE)
GO

CREATE TABLE [Tbl_Orders] (
  [ID] int  NOT NULL,
  [total] float(53)  NULL,
  [date] date  NULL,
  [userID] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [paymentTypeID] int  NULL,
  CONSTRAINT [PK_Tbl_Oders] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
GO
ALTER TABLE [Tbl_Orders] SET (LOCK_ESCALATION = TABLE)
GO

CREATE TABLE [Tbl_PaymentType] (
  [ID] int  NOT NULL,
  [name] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  CONSTRAINT [PK_Tbl_PaymentType] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
GO
ALTER TABLE [Tbl_PaymentType] SET (LOCK_ESCALATION = TABLE)
GO

CREATE TABLE [Tbl_Users] (
  [UserID] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Password] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [FullName] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [IsAdmin] bit  NULL,
  CONSTRAINT [PK_Tbl_Users] PRIMARY KEY CLUSTERED ([UserID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
GO
ALTER TABLE [Tbl_Users] SET (LOCK_ESCALATION = TABLE)
GO

ALTER TABLE [Tbl_CartDetail] ADD CONSTRAINT [FK_Tbl_CartDetail_Tbl_Users] FOREIGN KEY ([userID]) REFERENCES [Tbl_Users] ([UserID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [Tbl_CartDetail] ADD CONSTRAINT [FK_Tbl_CartDetail_Tbl_Foods] FOREIGN KEY ([foodID]) REFERENCES [Tbl_Foods] ([foodID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [Tbl_Foods] ADD CONSTRAINT [FK_Tbl_Foods_Tbl_Categorys] FOREIGN KEY ([categoryID]) REFERENCES [Tbl_Categorys] ([categoryID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [Tbl_OrderDetail] ADD CONSTRAINT [FK_Tbl_OrderDetail_Tbl_Oders] FOREIGN KEY ([orderID]) REFERENCES [Tbl_Orders] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [Tbl_OrderDetail] ADD CONSTRAINT [FK_Tbl_OrderDetail_Tbl_Foods] FOREIGN KEY ([foodID]) REFERENCES [Tbl_Foods] ([foodID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [Tbl_Orders] ADD CONSTRAINT [FK_Tbl_Oders_Tbl_Users] FOREIGN KEY ([userID]) REFERENCES [Tbl_Users] ([UserID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [Tbl_Orders] ADD CONSTRAINT [FK_Tbl_Oders_Tbl_PaymentType] FOREIGN KEY ([paymentTypeID]) REFERENCES [Tbl_PaymentType] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

