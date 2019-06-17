import Shared.IUserDAO;

import java.io.*;

public class FlatFileUserDAO implements IUserDAO {
    private static final String USER_FILENAME = FlatFileFactory.FLATFILE_DIR + "/userfile.txt";

    @Override
    public boolean checkUser(String username, String password) {

        return userExists(username, password);
    }

    @Override
    public boolean registerUser(String username, String password) {

        if(userExists(username, null)) {
            return false;
        }

        try {
            File file = new File(USER_FILENAME);

            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            pw.println(username + "," + password);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean userFileExists(File file) {

        return file.exists() && file.isFile();
    }

    private boolean userExists(String username, String password) {

        File file = new File(USER_FILENAME);

        if (userFileExists(file)) {

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {

                    line = line.trim();

                    String user = line.split(",")[0];

                    if (password == null) {

                        if (user.equals(username)) {

                            return true;
                        }
                    } else {

                        String pword = line.split(",")[1];

                        if (user.equals(username) && pword.equals(password)) {

                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}