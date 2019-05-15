package server;

public class DeleteCommand implements CommandInterface {

    public DeleteCommand() {}

    @Override
    public Object execute() throws Exception {

        boolean success = DataAccess.SINGLETON.delete();

        if(success) {
            System.out.println("Deleted data");
        } else {
            System.out.println("Did not delete data");
            throw new Exception("Delete failed");
        }

        return null;
    }


}