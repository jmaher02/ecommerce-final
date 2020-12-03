/** Program to write default users to the OutputStream
 * 
 * @author Jillian Maher
 *
 */

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminUserAccounts 
{
	public static void main(String[] args)
	{
		//Create default users
		User admin = new User("Username", "Admin", "LstName", "email@email.com", "admin");
		User test = new User("jillian.maher", "Jillian", "Maher", "jillian@maher.com", "FinalProject2020");
		
		try
		   {
		    FileOutputStream fos = new FileOutputStream
		                                  ( "userObjects", false );
		             // false means we will write to objects

		    ObjectOutputStream oos = new ObjectOutputStream( fos );

		    // write the objects to the file
		    oos.writeObject( admin );
		    oos.writeObject( test );
		    System.out.println(oos);

		    // release resources associated with the objects file
		    oos.close( );
		   }

		   catch ( FileNotFoundException fnfe )
		   {
		     System.out.println( "Unable to write to objects" );
		   }

		   catch ( IOException ioe )
		   {
		      ioe.printStackTrace( );
		   }
		
		System.out.println(admin);
	}
}
