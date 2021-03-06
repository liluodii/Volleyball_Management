﻿using System;
using System.Collections.Generic;
using System.Linq;

using System.Web.Http;

using Volleyball.Common;

using VolleyballService.Services;
using static Volleyball.EF.CustomClasses.CRequest;



namespace VolleyballAPI.Controllers
{
    public class TeamManagerController : ApiController
    {
        public BaseService _BaseService = new BaseService();
        TeamManagerService _Service = new TeamManagerService();



        [Route("AddEditPlayer")]
        [HttpPut]
        public GenericClass AddEditPlayer(CReqUpdateProfile Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.AddEditPlayer(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }



        [Route("DeletePlayer")]
        [HttpDelete]
        public GenericClass DeletePlayer(CReqDeleteUser Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.DeletePlayer(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch (Exception ex)
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }

        [Route("AddEditTeamManager")]
        [HttpPut]
        public GenericClass AddEditTeamManager(CReqAddEditTeamManager Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.AddEditTeamManager(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }



        [Route("DeleteTeamManager")]
        [HttpDelete]
        public GenericClass DeleteTeamManager(CReqDeleteUser Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.DeleteTeamManager(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch (Exception ex)
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }



        [Route("GetPlayerList")]
        [HttpGet]
        public GenericClass GetPlayerList(int UserID = 0, string Search = "", int IsCheckInTeam = 0)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (UserID == 0)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }
                Obj = _Service.GetPlayerList(UserID, Search, IsCheckInTeam);
                string JData1 = _BaseService.ConvertJsontoString(Obj);
                _BaseService.ValidateAPIToken("123", "", ActionContext.ActionDescriptor.ActionName, JData1);

            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
                Obj.Data = new List<int>();

            }
            return Obj;
        }

        [Route("GetTeamManagerDetails")]
        [HttpGet]
        public GenericClass GetTeamManagerDetails(int TeamManagerID = 0)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (TeamManagerID == 0)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                Obj = _Service.GetTeamManagerDetails(TeamManagerID);
                string JData1 = _BaseService.ConvertJsontoString(Obj);

                _BaseService.ValidateAPIToken("123", "", ActionContext.ActionDescriptor.ActionName, JData1);


            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
                Obj.
                    Data = new List<int>();

            }
            return Obj;
        }



        [Route("GetPlayerDetails")]
        [HttpGet]
        public GenericClass GetPlayerDetails(int PlayerID = 0)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (PlayerID == 0)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                Obj = _Service.GetPlayerDetails(PlayerID);

                string JData1 = _BaseService.ConvertJsontoString(Obj);

                _BaseService.ValidateAPIToken("123", "", ActionContext.ActionDescriptor.ActionName, JData1);

            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
                Obj.Data = new List<int>();

            }
            return Obj;
        }

        [Route("GetTeamManagerList")]
        [HttpGet]
        public GenericClass GetTeamManagerList(int UserID = 0, string Search = "")
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (UserID == 0)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                Obj = _Service.GetTeamManagerList(UserID, Search);

                string JData1 = _BaseService.ConvertJsontoString(Obj);

                _BaseService.ValidateAPIToken("123", "", ActionContext.ActionDescriptor.ActionName, JData1);

            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
                Obj.Data = new List<int>();

            }
            return Obj;
        }

    }
}