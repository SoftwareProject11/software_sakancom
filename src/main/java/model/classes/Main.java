package model.classes;

import code.classes.AddAdvertisement;
import code.classes.AdminPage;
import code.classes.Login;
import code.classes.OwnerControlPanel;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


public class Main {
    private static  Logger logger = Logger.getLogger(Main.class.getName());
    private static final String IN_VALID_INPUT = "Please enter valid input";
    private static final String BACK = "3- Back";

    public static void displayHouses(int ownerID){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select * from house where id_owner='"+ownerID+"'");
            logger.info("idHouse\t location\t\t services\t\t price");
            while (result.next()) {
                logger.info(result.getInt("idhouse")+"\t"+result.getString("location")+"\t\t"+result.getString("services")+"\t\t"+ result.getDouble("price")+" JD");
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }
    public static void main(String[] args) {
        Login log=new Login();
        AdminPage adminPage;
        AddAdvertisement advertisement;
        House newHouse;
        HouseFloor newHouseFloor;
        Scanner scan=new Scanner(System.in);
        Console console = System.console();
        logger.info("------* Welcome to SAKANCOM system *------");

        while (true){
            logger.info("-> Please, choose how you want to login to the system:");
            logger.info("1- Admin");
            logger.info("2- Tenant");
            logger.info("3- Owner");
            String choice=scan.nextLine();

            if(!choice.equals("1")&&!choice.equals("2")&&!choice.equals("3")){
                logger.info("The choice is not valid");
                continue;
            }
            userPass_loop:
            while (true){
                logger.info("Enter username: ");
                String username= scan.nextLine();
                logger.info("Enter password: ");
                String password= scan.nextLine();
                log.logInCheck(username,password,choice);
                if(!log.isLoggedIn()){
                    log.reasonFalseLogin();
                    continue;
                }
                if (choice.equals("1")){
                    logger.info("Hello "+username);
                    while (true){
                        logger.info("1- See the requests of advertisement to accept or reject it");
                        logger.info("2- See the reservations of houses");
                        logger.info("3- Log out");

                        String adminChoice= scan.nextLine();
                        if(adminChoice.equals("1")){
                            //open web page
                            while (true){
                                logger.info("1- Enter house id you want to accept its advertisement");
                                logger.info("2- <-Back");
                                String advChoice= scan.nextLine();
                                if (advChoice.equals("1")){
                                    logger.info("house id: ");
                                    String houseID= scan.nextLine();

                                    logger.info("acceptance (yes/no): ");
                                    String acceptance= scan.nextLine();
                                    adminPage=new AdminPage(Integer.parseInt(houseID),acceptance);
                                    AdminPage.acceptReject(adminPage);
                                    logger.info("DONE");
                                }
                                else if(advChoice.equals("2")){
                                    break;
                                }
                            }//while 1- accept

                        }//if admin 1
                        else if(adminChoice.equals("2")){
                            List<Tenant> tenantList = new ArrayList();
                            tenantList=AdminPage.seeReservations();
                            AdminPage.displayReservations(tenantList);
                        }//if admin 2
                        else if(adminChoice.equals("3")){
                            log.logout();
                            break userPass_loop;
                        }//if admin 3
                        else{
                            logger.warning(IN_VALID_INPUT);
                        }
                    }
                }//admin
//////////////////////////////////////////////////////////////////////////////////////////////////
                else if(choice.equals("3")){
                    logger.info("Hello "+log.getOwnerName());
                    owner_loop:
                    while (true){
                        logger.info("1- Add advertisement for an exist house");
                        logger.info("2- See all housing with all details");
                        logger.info("3- Add new house");
                        logger.info("4-log out");


                        String ownerChoice= scan.nextLine();
                        if(ownerChoice.equals("1")){
                            displayHouses(log.getOwnerID());
                            logger.info("Enter house id: ");
                            String houseID=scan.nextLine();
                            logger.info("Add photos: ");
                            String photo=scan.nextLine();
                            logger.info("Enter your name: ");
                            String name=scan.nextLine();
                            logger.info("Enter your contact info.: ");
                            String contact=scan.nextLine();
                            logger.info("Enter location: ");
                            String location=scan.nextLine();
                            logger.info("Enter services: ");
                            String services=scan.nextLine();
                            logger.info("Enter monthly rent: ");
                            String rent=scan.nextLine();
                            logger.info("Enter rent notes about inclusive of electricity and water or not: ");
                            String rentNote=scan.nextLine();
                            logger.info("Enter price: ");
                            String price=scan.nextLine();
                            advertisement=new AddAdvertisement(Integer.parseInt(houseID),photo,name,contact,location,services,Double.parseDouble(rent),rentNote,Double.parseDouble(price));
                            AddAdvertisement.addAdv(advertisement);
                            if(!AddAdvertisement.isValidHouse()){
                                if(AddAdvertisement.isDuplicateHouse){
                                    advertisement.displayReasonSameHouse();
                                }
                                else advertisement.displayReasonHouseNotExist();;
                            }
                        }//if owner 1
                        else if (ownerChoice.equals("2")) {
                            List<House> houseList=new ArrayList();
                            ArrayList apart=new ArrayList<>();
                            List<HouseFloor> apartInfoList = new ArrayList();
                            logger.info("Your Housing:");
                            displayHouses(log.getOwnerID());
                            logger.info("Enter house id to see number of tenant and floor of this house: ");
                            houseList=OwnerControlPanel.findHouse(scan.nextInt());
                            OwnerControlPanel.displayNOTenantAndFloors(houseList);
                            ////////////////
                            logger.info("Enter floor number you want to see its apartments: ");
                            apart=OwnerControlPanel.findFloor(scan.nextInt());
                            OwnerControlPanel.displayAparts(apart);
                            ///////////////
                            logger.info("Enter apart number that you want to see info. about it: ");
                            apartInfoList=OwnerControlPanel.findApart(scan.nextInt());
                            OwnerControlPanel.displayApartInformation(apartInfoList);
                        }//if owner 2
                        else if (ownerChoice.equals("3")) {
                            newHouse_loop:
                            while (true){
                                logger.info("Enter the required information for the new home: ");
                                logger.info("Enter house id: ");
                                String houseID=scan.nextLine();
                                logger.info("Add photos: ");
                                String photo=scan.nextLine();
                                logger.info("Enter location: ");
                                String location=scan.nextLine();
                                logger.info("Enter services: ");
                                String services=scan.nextLine();
                                logger.info("Enter price(JD): ");
                                String price=scan.nextLine();
                                logger.info("Enter number of floors: ");
                                String no_floors=scan.nextLine();
                                newHouse=new House(Integer.parseInt(houseID),photo,location,services,Double.parseDouble(price),log.getOwnerID(),Integer.parseInt(no_floors));
                                House.addHouse(newHouse);
                                if(!HouseFloor.findHouseFloorId(newHouse.getId())){
                                    details_loop:
                                    while (true){
                                        logger.info("Enter the details for this new new home: ");
                                        logger.info("Enter floor number: ");
                                        String idfloor=scan.nextLine();
                                        logger.info("Enter apartment number: ");
                                        String idapart=scan.nextLine();
                                        logger.info("Enter number of bathrooms for this apartment: ");
                                        String no_bathrooms=scan.nextLine();
                                        logger.info("Enter number of bedrooms for this apartment: ");
                                        String no_bedrooms=scan.nextLine();
                                        logger.info("Is there's a balcony? yes/no: ");
                                        String balcony=scan.nextLine();
                                        newHouseFloor=new HouseFloor(Integer.parseInt(houseID),Integer.parseInt(idfloor),Integer.parseInt(idapart),Integer.parseInt(no_bathrooms),Integer.parseInt(no_bedrooms),balcony);
                                        logger.info(newHouseFloor.getId_house()+"");
                                        House.addHouseInfo(newHouseFloor);
                                        /////////
                                        logger.info("1- continue adding details\n2- add another new house\n3- back to my page");
                                        String x=scan.nextLine();
                                        if(x.equals("1")){
                                            continue details_loop;
                                        } else if (x.equals("2")) {
                                            continue newHouse_loop;
                                        } else if (x.equals("3")) {
                                            continue owner_loop;
                                        }
                                    }
                                }else continue newHouse_loop;



                            }//newhouse loop
                        }//owner 3
                        else if (ownerChoice.equals("4")) {
                            log.logout();
                            break userPass_loop;
                        }
                        else{
                            logger.info(IN_VALID_INPUT);
                        }

                    }//owner loop
                }//choice 3
                else if (choice.equals("2")) {
                    
                }


            }
        }





    }//main

}//class
