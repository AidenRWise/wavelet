import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    ArrayList<String> list = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("This is the Start of the Search Engine");
        } else if (url.getPath().contains("/search")) {
            String finalResult = "";
            String[] searchResult = url.getQuery().split("=");
            if(searchResult[0].equals("s")){
                for(int i = 0; i<list.size();i++){
                    if(list.get(i).contains(searchResult[1]));
                        finalResult += ( list.get(i) + " ");
                }
                return finalResult;
            }

            return String.format(list.get(0));
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    list.add(parameters[1]);
                    return String.format("Added %s to Search! The Search now has %d results", parameters[1], list.size());
                }
            }
            return "404 Not Found!";
        }
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}