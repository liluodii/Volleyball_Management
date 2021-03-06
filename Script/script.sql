
USE [DB_A43E53_volleyball]
GO
/****** Object:  Table [dbo].[AppException]    Script Date: 06-21-2020 11:02:27 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AppException](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[UserID] [bigint] NULL,
	[Method] [nvarchar](255) NULL,
	[Page] [nvarchar](255) NULL,
	[Line] [varchar](max) NULL,
	[Message] [text] NULL,
	[CreatedDate] [datetime] NULL,
	[ExceptionType] [int] NULL,
	[IsSolved] [bit] NULL,
	[SolutionRemark] [varchar](200) NULL,
	[Object] [nvarchar](max) NULL,
 CONSTRAINT [PK_Exception] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LeagueManager]    Script Date: 06-21-2020 11:02:29 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LeagueManager](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](200) NULL,
	[LastName] [varchar](200) NULL,
	[Address] [varchar](300) NULL,
	[ProfilePic] [varchar](300) NULL,
	[JoinDate] [datetime] NULL,
	[UserID] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[ModifiedDate] [datetime] NULL,
	[DOB] [datetime] NULL,
	[Gender] [varchar](20) NULL,
	[Contact] [varchar](20) NULL,
 CONSTRAINT [PK_LeagueManager] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PlayerMaster]    Script Date: 06-21-2020 11:02:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlayerMaster](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NULL,
	[FirstName] [varchar](200) NULL,
	[LastName] [varchar](200) NULL,
	[ProfilePic] [varchar](200) NULL,
	[Gender] [varchar](10) NULL,
	[Address] [varchar](max) NULL,
	[Contact] [varchar](200) NULL,
	[DOB] [datetime] NULL,
	[JoinDate] [datetime] NULL,
	[Experience] [decimal](18, 2) NULL,
	[CreatedDate] [datetime] NULL,
	[ModifiedDate] [datetime] NULL,
 CONSTRAINT [PK_PlayerMaster] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RoleMaster]    Script Date: 06-21-2020 11:02:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoleMaster](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_RoleMaster] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Team]    Script Date: 06-21-2020 11:02:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Team](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TeamName] [varchar](300) NULL,
	[TeamPic] [varchar](300) NULL,
	[TeamManagerID] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[ModifiedDate] [datetime] NULL,
	[CreatedUserID] [int] NULL,
 CONSTRAINT [PK_Team] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamManager]    Script Date: 06-21-2020 11:02:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamManager](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](200) NULL,
	[LastName] [varchar](200) NULL,
	[Address] [varchar](200) NULL,
	[ProfilePic] [varchar](200) NULL,
	[JoinDate] [datetime] NULL,
	[UserID] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[ModifiedDate] [datetime] NULL,
	[DOB] [datetime] NULL,
	[Contact] [varchar](20) NULL,
	[Gender] [varchar](20) NULL,
 CONSTRAINT [PK_TeamManager] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamMember]    Script Date: 06-21-2020 11:02:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamMember](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TeamID] [int] NULL,
	[PlayerID] [int] NULL,
	[RoleID] [int] NULL,
	[OrderID] [int] NULL,
 CONSTRAINT [PK_TeamMamber_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tournament]    Script Date: 06-21-2020 11:02:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tournament](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](200) NULL,
	[StartDate] [datetime] NULL,
	[EndDate] [datetime] NULL,
	[CreatedDate] [datetime] NULL,
	[ModifiedDate] [datetime] NULL,
 CONSTRAINT [PK_Tournament] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TournamentTeam]    Script Date: 06-21-2020 11:02:33 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TournamentTeam](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TournamentID] [int] NULL,
	[Team1] [int] NULL,
	[Team2] [int] NULL,
	[MatchDate] [datetime] NULL,
	[CreatedDate] [datetime] NULL,
	[Team1Score] [int] NULL,
	[Team2Score] [int] NULL,
 CONSTRAINT [PK_TournamentTeam] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserMaster]    Script Date: 06-21-2020 11:02:33 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserMaster](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[EmailID] [varchar](200) NOT NULL,
	[Password] [varchar](200) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[ModifiedDate] [datetime] NULL,
	[RoleID] [int] NULL,
 CONSTRAINT [PK_UserMaster] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[AppException] ON 

INSERT [dbo].[AppException] ([ID], [UserID], [Method], [Page], [Line], [Message], [CreatedDate], [ExceptionType], [IsSolved], [SolutionRemark], [Object]) VALUES (26, NULL, N'UpdateProfile', N'', N'0', NULL, CAST(N'2020-06-21T05:29:29.607' AS DateTime), 2, 0, NULL, N'{"APIKey":"123","UserID":3,"EmailID":null,"FirstName":"league","Password":null,"LastName":"l1","Gender":"female","Contact":"1234567890","DOB":"2010-07-31T00:00:00","JoinDate":null,"Experience":null,"Address":"cn"}')
INSERT [dbo].[AppException] ([ID], [UserID], [Method], [Page], [Line], [Message], [CreatedDate], [ExceptionType], [IsSolved], [SolutionRemark], [Object]) VALUES (27, NULL, N'UpdateProfile', N'', N'0', NULL, CAST(N'2020-06-21T05:30:36.030' AS DateTime), 2, 0, NULL, N'{"APIKey":"123","UserID":2,"EmailID":null,"FirstName":"t1","Password":null,"LastName":"t1","Gender":"male","Contact":"1234567890","DOB":"2002-06-27T00:00:00","JoinDate":null,"Experience":null,"Address":"canada"}')
INSERT [dbo].[AppException] ([ID], [UserID], [Method], [Page], [Line], [Message], [CreatedDate], [ExceptionType], [IsSolved], [SolutionRemark], [Object]) VALUES (28, NULL, N'UpdateProfile', N'', N'0', NULL, CAST(N'2020-06-21T05:30:45.877' AS DateTime), 2, 0, NULL, N'{"APIKey":"123","UserID":2,"EmailID":null,"FirstName":"t1","Password":null,"LastName":"t1","Gender":"male","Contact":"1234567890","DOB":"2002-06-04T00:00:00","JoinDate":null,"Experience":null,"Address":"canada"}')
SET IDENTITY_INSERT [dbo].[AppException] OFF
SET IDENTITY_INSERT [dbo].[LeagueManager] ON 

INSERT [dbo].[LeagueManager] ([ID], [FirstName], [LastName], [Address], [ProfilePic], [JoinDate], [UserID], [CreatedDate], [ModifiedDate], [DOB], [Gender], [Contact]) VALUES (1, N'league', N'l1', N'cn', N'Images/User/3/d3254745-832b-46ea-8c4c-366edaff1076.png', CAST(N'2020-05-02T00:00:00.000' AS DateTime), 3, CAST(N'2020-03-06T00:00:00.000' AS DateTime), NULL, CAST(N'2002-06-21T00:00:00.000' AS DateTime), N'female', N'1234567890')
SET IDENTITY_INSERT [dbo].[LeagueManager] OFF
SET IDENTITY_INSERT [dbo].[PlayerMaster] ON 

INSERT [dbo].[PlayerMaster] ([ID], [UserID], [FirstName], [LastName], [ProfilePic], [Gender], [Address], [Contact], [DOB], [JoinDate], [Experience], [CreatedDate], [ModifiedDate]) VALUES (8, 23, N'Krishana', N'naik', N'Images/Provider/23/ebc21cb5-acef-4797-ac0e-89dfa3f8d3c0.png', N'female', N'rue saint laurent', N'5246808526', CAST(N'1998-06-24T00:00:00.000' AS DateTime), CAST(N'2002-06-14T00:00:00.000' AS DateTime), CAST(1.00 AS Decimal(18, 2)), CAST(N'2020-06-14T15:03:09.993' AS DateTime), NULL)
INSERT [dbo].[PlayerMaster] ([ID], [UserID], [FirstName], [LastName], [ProfilePic], [Gender], [Address], [Contact], [DOB], [JoinDate], [Experience], [CreatedDate], [ModifiedDate]) VALUES (9, 25, N'pulkit', N'sharma', N'Images/Provider/25/5d4d7580-6792-4bf8-8076-293fc62e0622.png', N'male', N'rue mark', N'2586478052', CAST(N'2020-06-21T00:00:00.000' AS DateTime), CAST(N'2002-06-14T00:00:00.000' AS DateTime), CAST(1.00 AS Decimal(18, 2)), CAST(N'2020-06-14T15:46:08.357' AS DateTime), CAST(N'2020-06-21T05:00:58.730' AS DateTime))
SET IDENTITY_INSERT [dbo].[PlayerMaster] OFF
SET IDENTITY_INSERT [dbo].[RoleMaster] ON 

INSERT [dbo].[RoleMaster] ([ID], [Name]) VALUES (1, N'Admin')
INSERT [dbo].[RoleMaster] ([ID], [Name]) VALUES (2, N'LeagueManager')
INSERT [dbo].[RoleMaster] ([ID], [Name]) VALUES (3, N'TeamManager')
INSERT [dbo].[RoleMaster] ([ID], [Name]) VALUES (4, N'Player')
SET IDENTITY_INSERT [dbo].[RoleMaster] OFF
SET IDENTITY_INSERT [dbo].[Team] ON 

INSERT [dbo].[Team] ([ID], [TeamName], [TeamPic], [TeamManagerID], [CreatedDate], [ModifiedDate], [CreatedUserID]) VALUES (23, N'RRJ', N'Images/abd8b7a3-4c0f-44cf-9482-9b314887210e.png', 1, CAST(N'2020-06-14T16:37:55.463' AS DateTime), NULL, 3)
INSERT [dbo].[Team] ([ID], [TeamName], [TeamPic], [TeamManagerID], [CreatedDate], [ModifiedDate], [CreatedUserID]) VALUES (24, N'RAB', N'Images/b19c7ed3-52f5-47e6-9c45-11fc96b36cf6.png', 14, CAST(N'2020-06-14T16:47:22.413' AS DateTime), NULL, 3)
INSERT [dbo].[Team] ([ID], [TeamName], [TeamPic], [TeamManagerID], [CreatedDate], [ModifiedDate], [CreatedUserID]) VALUES (25, N'PPR', N'Images/b19c7ed3-52f5-47e6-9c45-11fc96b36cf6.png', 13, CAST(N'2020-06-14T16:47:22.413' AS DateTime), NULL, 3)
INSERT [dbo].[Team] ([ID], [TeamName], [TeamPic], [TeamManagerID], [CreatedDate], [ModifiedDate], [CreatedUserID]) VALUES (26, N'ERC', N'Images/b19c7ed3-52f5-47e6-9c45-11fc96b36cf6.png', 12, CAST(N'2020-06-18T00:00:00.000' AS DateTime), NULL, 3)
SET IDENTITY_INSERT [dbo].[Team] OFF
SET IDENTITY_INSERT [dbo].[TeamManager] ON 

INSERT [dbo].[TeamManager] ([ID], [FirstName], [LastName], [Address], [ProfilePic], [JoinDate], [UserID], [CreatedDate], [ModifiedDate], [DOB], [Contact], [Gender]) VALUES (1, N't1', N't1', N'canada', N'Images/Provider/2/8d93e4b1-2e6c-48e2-b0af-1c1de47742f3.png', CAST(N'2020-01-01T00:00:00.000' AS DateTime), 2, CAST(N'2020-05-02T00:00:00.000' AS DateTime), CAST(N'2020-06-21T05:26:41.453' AS DateTime), CAST(N'2002-06-04T00:00:00.000' AS DateTime), N'1234567890', N'male')
INSERT [dbo].[TeamManager] ([ID], [FirstName], [LastName], [Address], [ProfilePic], [JoinDate], [UserID], [CreatedDate], [ModifiedDate], [DOB], [Contact], [Gender]) VALUES (12, N'Lilu', N'Odedra', N'rue saint marc', N'Images/Provider/22/7a5cb8ae-8596-4bac-ac35-6dc0bb21a53b.png', CAST(N'2002-06-14T00:00:00.000' AS DateTime), 22, CAST(N'2020-06-14T14:58:30.067' AS DateTime), NULL, CAST(N'1997-06-14T00:00:00.000' AS DateTime), N'8525680526', N'female')
INSERT [dbo].[TeamManager] ([ID], [FirstName], [LastName], [Address], [ProfilePic], [JoinDate], [UserID], [CreatedDate], [ModifiedDate], [DOB], [Contact], [Gender]) VALUES (13, N'Urvi', N'arora', N'rue matc', NULL, CAST(N'2002-06-03T00:00:00.000' AS DateTime), 24, CAST(N'2020-06-14T15:05:08.773' AS DateTime), NULL, CAST(N'2002-06-14T00:00:00.000' AS DateTime), N'8526587425', N'female')
INSERT [dbo].[TeamManager] ([ID], [FirstName], [LastName], [Address], [ProfilePic], [JoinDate], [UserID], [CreatedDate], [ModifiedDate], [DOB], [Contact], [Gender]) VALUES (14, N'krishna', N'naik', N'saint rue', N'Images/Provider/28/a4ad4cc0-d555-4184-8f44-52338c25c4ee.png', CAST(N'2002-06-18T00:00:00.000' AS DateTime), 28, CAST(N'2020-06-14T16:45:42.443' AS DateTime), NULL, CAST(N'2002-06-12T00:00:00.000' AS DateTime), N'3256984720', N'female')
INSERT [dbo].[TeamManager] ([ID], [FirstName], [LastName], [Address], [ProfilePic], [JoinDate], [UserID], [CreatedDate], [ModifiedDate], [DOB], [Contact], [Gender]) VALUES (15, N'lilu', N'odedra', N'saint mAfk', N'Images/Provider/29/105bfc49-4f77-4a54-b919-5da11e674219.png', CAST(N'2002-06-19T00:00:00.000' AS DateTime), 29, CAST(N'2020-06-21T04:17:49.110' AS DateTime), NULL, CAST(N'2002-06-21T00:00:00.000' AS DateTime), N'2583695823', N'male')
SET IDENTITY_INSERT [dbo].[TeamManager] OFF
SET IDENTITY_INSERT [dbo].[TeamMember] ON 

INSERT [dbo].[TeamMember] ([ID], [TeamID], [PlayerID], [RoleID], [OrderID]) VALUES (21, 23, 8, NULL, 1)
INSERT [dbo].[TeamMember] ([ID], [TeamID], [PlayerID], [RoleID], [OrderID]) VALUES (23, 23, 9, NULL, 1)
SET IDENTITY_INSERT [dbo].[TeamMember] OFF
SET IDENTITY_INSERT [dbo].[Tournament] ON 

INSERT [dbo].[Tournament] ([ID], [Name], [StartDate], [EndDate], [CreatedDate], [ModifiedDate]) VALUES (1, N'ILP INDIA', NULL, NULL, CAST(N'2020-01-01T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[Tournament] ([ID], [Name], [StartDate], [EndDate], [CreatedDate], [ModifiedDate]) VALUES (3, N'Gujarati', NULL, NULL, CAST(N'2020-06-19T00:55:15.980' AS DateTime), NULL)
INSERT [dbo].[Tournament] ([ID], [Name], [StartDate], [EndDate], [CreatedDate], [ModifiedDate]) VALUES (4, N'Summer', NULL, NULL, CAST(N'2020-06-19T00:58:27.067' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[Tournament] OFF
SET IDENTITY_INSERT [dbo].[TournamentTeam] ON 

INSERT [dbo].[TournamentTeam] ([ID], [TournamentID], [Team1], [Team2], [MatchDate], [CreatedDate], [Team1Score], [Team2Score]) VALUES (7, 1, 23, 24, CAST(N'2020-06-19T00:00:00.000' AS DateTime), CAST(N'2020-06-18T20:48:13.993' AS DateTime), 25, 26)
INSERT [dbo].[TournamentTeam] ([ID], [TournamentID], [Team1], [Team2], [MatchDate], [CreatedDate], [Team1Score], [Team2Score]) VALUES (8, 1, 25, 26, CAST(N'2020-06-26T00:00:00.000' AS DateTime), CAST(N'2020-06-20T19:13:09.833' AS DateTime), 0, 0)
INSERT [dbo].[TournamentTeam] ([ID], [TournamentID], [Team1], [Team2], [MatchDate], [CreatedDate], [Team1Score], [Team2Score]) VALUES (9, 3, 24, 23, CAST(N'2020-06-21T00:00:00.000' AS DateTime), CAST(N'2020-06-20T19:14:50.757' AS DateTime), 5, 3)
INSERT [dbo].[TournamentTeam] ([ID], [TournamentID], [Team1], [Team2], [MatchDate], [CreatedDate], [Team1Score], [Team2Score]) VALUES (15, 4, 25, 23, CAST(N'2020-06-10T00:00:00.000' AS DateTime), CAST(N'2020-06-21T04:59:47.260' AS DateTime), 0, 0)
SET IDENTITY_INSERT [dbo].[TournamentTeam] OFF
SET IDENTITY_INSERT [dbo].[UserMaster] ON 

INSERT [dbo].[UserMaster] ([ID], [EmailID], [Password], [CreatedDate], [ModifiedDate], [RoleID]) VALUES (2, N'team@team.com', N'123456', CAST(N'2020-06-02T00:00:00.000' AS DateTime), NULL, 3)
INSERT [dbo].[UserMaster] ([ID], [EmailID], [Password], [CreatedDate], [ModifiedDate], [RoleID]) VALUES (3, N'league@league.com', N'123456', CAST(N'2020-06-02T00:00:00.000' AS DateTime), NULL, 2)
INSERT [dbo].[UserMaster] ([ID], [EmailID], [Password], [CreatedDate], [ModifiedDate], [RoleID]) VALUES (22, N'liluodedra13@gmail.com', N'123456', CAST(N'2020-06-14T14:58:30.050' AS DateTime), NULL, 3)
INSERT [dbo].[UserMaster] ([ID], [EmailID], [Password], [CreatedDate], [ModifiedDate], [RoleID]) VALUES (23, N'krishnanaik@gmail.com', N'123456', CAST(N'2020-06-14T15:03:09.993' AS DateTime), NULL, 4)
INSERT [dbo].[UserMaster] ([ID], [EmailID], [Password], [CreatedDate], [ModifiedDate], [RoleID]) VALUES (24, N'urviarora8115@gmail.com', N'123456', CAST(N'2020-06-14T15:05:08.667' AS DateTime), NULL, 3)
INSERT [dbo].[UserMaster] ([ID], [EmailID], [Password], [CreatedDate], [ModifiedDate], [RoleID]) VALUES (25, N'psharma@gmail.com', N'123456', CAST(N'2020-06-14T15:46:08.340' AS DateTime), NULL, 4)
INSERT [dbo].[UserMaster] ([ID], [EmailID], [Password], [CreatedDate], [ModifiedDate], [RoleID]) VALUES (28, N'naikkrish28@gmail.com', N'123456', CAST(N'2020-06-14T16:45:42.427' AS DateTime), NULL, 3)
INSERT [dbo].[UserMaster] ([ID], [EmailID], [Password], [CreatedDate], [ModifiedDate], [RoleID]) VALUES (29, N'liluodedra13r@gmail.com', N'123456', CAST(N'2020-06-21T04:17:49.047' AS DateTime), NULL, 3)
SET IDENTITY_INSERT [dbo].[UserMaster] OFF
ALTER TABLE [dbo].[AppException] ADD  CONSTRAINT [DF_Exceptions_is_solved]  DEFAULT ((0)) FOR [IsSolved]
GO
ALTER TABLE [dbo].[LeagueManager]  WITH CHECK ADD  CONSTRAINT [FK_LeagueManager_LeagueManager] FOREIGN KEY([UserID])
REFERENCES [dbo].[UserMaster] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LeagueManager] CHECK CONSTRAINT [FK_LeagueManager_LeagueManager]
GO
ALTER TABLE [dbo].[PlayerMaster]  WITH CHECK ADD  CONSTRAINT [FK_PlayerMaster_UserMaster] FOREIGN KEY([UserID])
REFERENCES [dbo].[UserMaster] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PlayerMaster] CHECK CONSTRAINT [FK_PlayerMaster_UserMaster]
GO
ALTER TABLE [dbo].[Team]  WITH CHECK ADD  CONSTRAINT [FK_Team_TeamManager] FOREIGN KEY([TeamManagerID])
REFERENCES [dbo].[TeamManager] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Team] CHECK CONSTRAINT [FK_Team_TeamManager]
GO
ALTER TABLE [dbo].[Team]  WITH CHECK ADD  CONSTRAINT [FK_Team_UserMaster] FOREIGN KEY([CreatedUserID])
REFERENCES [dbo].[UserMaster] ([ID])
GO
ALTER TABLE [dbo].[Team] CHECK CONSTRAINT [FK_Team_UserMaster]
GO
ALTER TABLE [dbo].[TeamManager]  WITH CHECK ADD  CONSTRAINT [FK_TeamManager_UserMaster] FOREIGN KEY([UserID])
REFERENCES [dbo].[UserMaster] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TeamManager] CHECK CONSTRAINT [FK_TeamManager_UserMaster]
GO
ALTER TABLE [dbo].[TeamMember]  WITH CHECK ADD  CONSTRAINT [FK_TeamMamber_PlayerMaster] FOREIGN KEY([PlayerID])
REFERENCES [dbo].[PlayerMaster] ([ID])
GO
ALTER TABLE [dbo].[TeamMember] CHECK CONSTRAINT [FK_TeamMamber_PlayerMaster]
GO
ALTER TABLE [dbo].[TeamMember]  WITH CHECK ADD  CONSTRAINT [FK_TeamMamber_Team] FOREIGN KEY([TeamID])
REFERENCES [dbo].[Team] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TeamMember] CHECK CONSTRAINT [FK_TeamMamber_Team]
GO
ALTER TABLE [dbo].[TournamentTeam]  WITH CHECK ADD  CONSTRAINT [FK_TournamentTeam_Team] FOREIGN KEY([Team1])
REFERENCES [dbo].[Team] ([ID])
GO
ALTER TABLE [dbo].[TournamentTeam] CHECK CONSTRAINT [FK_TournamentTeam_Team]
GO
ALTER TABLE [dbo].[TournamentTeam]  WITH CHECK ADD  CONSTRAINT [FK_TournamentTeam_Team1] FOREIGN KEY([Team2])
REFERENCES [dbo].[Team] ([ID])
GO
ALTER TABLE [dbo].[TournamentTeam] CHECK CONSTRAINT [FK_TournamentTeam_Team1]
GO
ALTER TABLE [dbo].[TournamentTeam]  WITH CHECK ADD  CONSTRAINT [FK_TournamentTeam_Tournament] FOREIGN KEY([TournamentID])
REFERENCES [dbo].[Tournament] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TournamentTeam] CHECK CONSTRAINT [FK_TournamentTeam_Tournament]
GO
ALTER TABLE [dbo].[UserMaster]  WITH CHECK ADD  CONSTRAINT [FK_UserMaster_RoleMaster] FOREIGN KEY([RoleID])
REFERENCES [dbo].[RoleMaster] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[UserMaster] CHECK CONSTRAINT [FK_UserMaster_RoleMaster]
GO
USE [master]
GO
ALTER DATABASE [DB_A43E53_volleyball] SET  READ_WRITE 
GO
