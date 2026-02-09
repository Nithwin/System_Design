class Admin {
  String adminId;
  String password;

  public Admin(String adminId,String password){
    this.adminId=adminId;
    this.password=password;
  }
  boolean login(String id,String pass){
    return adminId.equals(id) && password.equals(pass);
  }

}
