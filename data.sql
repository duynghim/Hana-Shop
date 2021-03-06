USE [J3.L.P0013]
GO
INSERT [dbo].[Tbl_Users] ([UserID], [Password], [FullName], [IsAdmin]) VALUES (N'1', N'1', N'Pham Cong Thanh', 1)
INSERT [dbo].[Tbl_Users] ([UserID], [Password], [FullName], [IsAdmin]) VALUES (N'2', N'2', N'Tran Duy Nghiem', 0)
GO
INSERT [dbo].[Tbl_PaymentType] ([ID], [name]) VALUES (1, N'COD')
GO
INSERT [dbo].[Tbl_Orders] ([ID], [total], [date], [userID], [paymentTypeID]) VALUES (1, 49000, CAST(N'2021-01-20' AS Date), N'2', 1)
INSERT [dbo].[Tbl_Orders] ([ID], [total], [date], [userID], [paymentTypeID]) VALUES (2, 40000, CAST(N'2021-01-20' AS Date), N'2', 1)
INSERT [dbo].[Tbl_Orders] ([ID], [total], [date], [userID], [paymentTypeID]) VALUES (3, 40000, CAST(N'2021-01-20' AS Date), N'2', 1)
INSERT [dbo].[Tbl_Orders] ([ID], [total], [date], [userID], [paymentTypeID]) VALUES (4, 200, CAST(N'2021-01-19' AS Date), N'2', 1)
GO
INSERT [dbo].[Tbl_Categorys] ([categoryID], [categoryName]) VALUES (1, N'Com Tam')
INSERT [dbo].[Tbl_Categorys] ([categoryID], [categoryName]) VALUES (2, N'Pho')
GO
INSERT [dbo].[Tbl_Foods] ([foodID], [foodName], [createDate], [categoryID], [image], [quantity], [price], [status]) VALUES (1, N'Com Suon Cha', CAST(N'2021-01-20' AS Date), 1, N'Image\ComSuonCha.jpg', 10, 49000, 1)
INSERT [dbo].[Tbl_Foods] ([foodID], [foodName], [createDate], [categoryID], [image], [quantity], [price], [status]) VALUES (2, N'Com Tam Bi Cha', CAST(N'2021-01-20' AS Date), 1, N'Image\ComBiCha.jpg', 10, 40000, 1)
INSERT [dbo].[Tbl_Foods] ([foodID], [foodName], [createDate], [categoryID], [image], [quantity], [price], [status]) VALUES (3, N'Com Cha Op La', CAST(N'2021-01-20' AS Date), 1, N'Image\ComChaOpLa.jpg', 11, 40000, 1)
INSERT [dbo].[Tbl_Foods] ([foodID], [foodName], [createDate], [categoryID], [image], [quantity], [price], [status]) VALUES (4, N'Com Suon', CAST(N'2021-01-20' AS Date), 1, N'Image\ComSuon.jpg', 10, 40000, 1)
INSERT [dbo].[Tbl_Foods] ([foodID], [foodName], [createDate], [categoryID], [image], [quantity], [price], [status]) VALUES (5, N'Com Suon Cha Op La', CAST(N'2021-01-20' AS Date), 1, N'Image\ComSuonChaOpLa.jpg', 11, 54000, 1)
INSERT [dbo].[Tbl_Foods] ([foodID], [foodName], [createDate], [categoryID], [image], [quantity], [price], [status]) VALUES (6, N'Com Suon Op La', CAST(N'2021-01-20' AS Date), 1, N'Image\ComSuonOpLa.jpg', 11, 123, 0)
INSERT [dbo].[Tbl_Foods] ([foodID], [foodName], [createDate], [categoryID], [image], [quantity], [price], [status]) VALUES (7, N'Com Suon Op La', CAST(N'2021-01-20' AS Date), 1, N'Image\ComSuonOpLa.jpg', 11, 495000, 1)
GO
INSERT [dbo].[Tbl_OrderDetail] ([ID], [orderID], [foodID], [foodName], [quantity], [price]) VALUES (1, 1, 1, N'Com Suon Cha', 1, 49000)
INSERT [dbo].[Tbl_OrderDetail] ([ID], [orderID], [foodID], [foodName], [quantity], [price]) VALUES (2, 2, 2, N'Com Tam Bi Cha', 1, 40000)
INSERT [dbo].[Tbl_OrderDetail] ([ID], [orderID], [foodID], [foodName], [quantity], [price]) VALUES (3, 3, 4, N'Com Suon', 1, 40000)
GO
