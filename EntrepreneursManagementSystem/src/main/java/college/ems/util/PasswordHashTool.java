package college.ems.util;
import org.mindrot.jbcrypt.BCrypt;
/** Run with one password argument to print a BCrypt hash for users.password_hash. */
public final class PasswordHashTool{public static void main(String[] args){if(args.length!=1){System.err.println("Usage: PasswordHashTool <new-password>");System.exit(1);}System.out.println(BCrypt.hashpw(args[0],BCrypt.gensalt(12)));}}
