//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Volleyball.EF
{
    using System;
    using System.Collections.Generic;
    
    public partial class TeamMember
    {
        public int ID { get; set; }
        public Nullable<int> TeamID { get; set; }
        public Nullable<int> PlayerID { get; set; }
        public Nullable<int> RoleID { get; set; }
        public Nullable<int> OrderID { get; set; }
    
        public virtual PlayerMaster PlayerMaster { get; set; }
        public virtual Team Team { get; set; }
    }
}
