
import java.util.InputMismatchException;
import java.util.Scanner;

import java.sql.*;

public class App {
	
	//static Scanner scanner;
	static Connection conn;
		
	public static void main(String[] args) throws Exception {
    
		
		try (Scanner input = new Scanner (System.in)) {
      String pilihanUser;
      boolean isLanjutkan = true;
      				
      String url = "jdbc:mysql://localhost:3306/db_pegawai";
      try {
      	Class.forName("com.mysql.cj.jdbc.Driver");
      	conn = DriverManager.getConnection(url,"root","");
      	System.out.println("Class Driver ditemukan");
      	
      	while (isLanjutkan) {
      		System.out.println("\n------------------");
      		System.out.println("Database Pegawai");
      		System.out.println("------------------");
      		System.out.println("1. Lihat Data Pegawai");
      		System.out.println("2. Tambah Data Pegawai");
      		System.out.println("3. Update Data Pegawai");
      		System.out.println("4. Delete Data Pegawai");
      		System.out.println("5. Cari Data Pegawai");
      		
      		System.out.print("\nMasukkan Pilihan : ");
      		pilihanUser = input.next();
      		
      		switch (pilihanUser) {
      		case "1":
      			lihatdata();
      			break;
      		case "2":
      			tambahdata();
      			break;
      		case "3":
      			ubahdata();
      			break;
      		case "4":
      			hapusdata();
      			break;
      		case "5":
      			caridata();
      			break;
      		default:
      			System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
      		}
      		
      		System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
      		pilihanUser = input.next();
      		isLanjutkan = pilihanUser.equalsIgnoreCase("y");
      	}
      	System.out.println("\nTerima Kasih");
      	
      }
      catch(ClassNotFoundException ex) {
      	System.err.println("Driver Error");
      	System.exit(0);
      }
      catch(SQLException e){
      	System.err.println("Tidak berhasil koneksi");
      }
    }
	}
	

	private static void lihatdata() throws SQLException {
		String text1 = "\n=== Data Pegawai===";
		System.out.println(text1.toUpperCase());
						
		String sql ="SELECT * FROM data_pegawai";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nno Pegawai\t: ");
            System.out.print(result.getInt("noPegawai"));
            System.out.print("\nNama Pegawai\t: ");
            System.out.print(result.getString("NamaPegawai"));
            System.out.print("\nJabatan\t: ");
            System.out.print(result.getString("Jabatan"));
            System.out.print("\n");
		}
	}
		
	private static void tambahdata() throws SQLException{
		String text2 = "\n===Tambah Data Pegawai===";
		System.out.println(text2.toUpperCase());
		
		try (Scanner input = new Scanner (System.in)) {
      try {
    
      Gaji gaji1 = new Gaji();
      gaji1.NoPegawai();
       String noPegawai = input.nextLine();
      gaji1.NamaPegawai();
       String NamaPegawai = input.nextLine();
      gaji1.Jabatan();
       String Jabatan = input.nextLine();
      
      String sql = "INSERT INTO data_pegawai (noPegawai, NamaPegawai, Jabatan) VALUES ('"+noPegawai+"','"+NamaPegawai+"','"+Jabatan+"')";
      			
          Statement statement = conn.createStatement();
          statement.execute(sql);
          System.out.println("\nBerhasil input data pegawai");

        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan input data");
        } catch (InputMismatchException e) {
        	System.err.println("Inputlah dengan angka saja");
       	}
    }
	}

  private static void ubahdata() throws SQLException{
		String text3 = "\n===Ubah Data Pegawai===";
		System.out.println(text3.toUpperCase());
		
		try (Scanner input = new Scanner (System.in)) {
      try {
              lihatdata();
              System.out.print("Masukkan nomor Pegawai yang akan di ubah atau update : ");
              Integer noPegawai = Integer.parseInt(input.nextLine());
              
              String sql = "SELECT * FROM data_pegawai WHERE  noPegawai = " +noPegawai;
              
              Statement statement = conn.createStatement();
              ResultSet result = statement.executeQuery(sql);
              
              if(result.next()){
                  
                  System.out.print("NamaPegawai ["+result.getString("NamaPegawai")+"]\t: ");
                  String NamaPegawai = input.nextLine();
                     
                  sql = "UPDATE data_pegawai SET NamaPegawai='"+NamaPegawai+"' WHERE noPegawai='"+noPegawai+"'";
                  //System.out.println(sql);

                  if(statement.executeUpdate(sql) > 0){
                      System.out.println("Berhasil memperbaharui data pegawai (noPegawai "+noPegawai+")");
                  }
              }
              statement.close();        
          } catch (SQLException e) {
              System.err.println("Terjadi kesalahan dalam mengedit data");
              System.err.println(e.getMessage());
          }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
		}
	
	private static void hapusdata() {
		String text4 = "\n===Hapus Data Pegawai===";
		System.out.println(text4.toUpperCase());
		
		try (Scanner input = new Scanner (System.in)) {
      try{
            lihatdata();
            System.out.print("Ketik nomor Pegawai pegawai yang akan Anda Hapus : ");
            Integer noPegawai= Integer.parseInt(input.nextLine());
            
            String sql = "DELETE FROM data_pegawai WHERE  noPegawai = "+ noPegawai;
            Statement statement = conn.createStatement();
            //ResultSet result = statement.executeQuery(sql);
            
            if(statement.executeUpdate(sql) > 0){
                System.out.println("Berhasil menghapus data pegawai (noPegawai "+noPegawai+")");
            }
       }catch(SQLException e){
            System.out.println("Terjadi kesalahan dalam menghapus data barang");
            }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
		}
	
	private static void caridata () throws SQLException {
		String text5 = "\n===Cari Data Pegawai===";
		System.out.println(text5.toUpperCase());
		
		try (Scanner input = new Scanner (System.in)) {
      System.out.print("Masukkan Nama Pegawai : ");
          
      String keyword = input.nextLine();
          Statement statement = conn.createStatement();
          String sql = "SELECT * FROM data_pegawai WHERE NamaPegawai LIKE '%"+keyword+"%'";
          ResultSet result = statement.executeQuery(sql); 
                  
          while(result.next()){
          	System.out.print("\nnoPegawai\t: ");
              System.out.print(result.getInt("noPegawai"));
              System.out.print("\nNamaPegawai\t: ");
              System.out.print(result.getString("NamaPegawai"));
          }
    }
	}
	
}

