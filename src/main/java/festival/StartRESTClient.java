package festival;

import festival.model.Artist;
import festival.restClient.ArtistClient;
import festival.restService.ServiceException;
import org.springframework.web.client.RestClientException;

public class StartRESTClient {
    private final static ArtistClient artistClient =new ArtistClient();
    public static void main(String[] args) {
        getAll("GET ALL:");
        adaugare();
        actualizare();
       // stergere();
    }

    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServiceException e) {
            System.out.println("Service exception"+ e);
        }
    }

    private static void getAll(String operatie){
        try{
            show(()->{
                Artist[] all= artistClient.getAll();
                System.out.println("\n\n"+operatie);
                for(Artist art:all){
                    System.out.println(art);
                }
                System.out.println("\n\n");
            });
        }catch(RestClientException ex){
            System.out.println("Exception ... "+ex.getMessage());
        }
    }

    private static void adaugare(){
        try{
            show(()->{
                Artist artist=new Artist("ArtistNou","LocNou",100,100,"10-06-2020","19:50");
                artistClient.create(artist);
                getAll("ADAUGARE");
            });
        }catch(RestClientException ex){
            System.out.println("Exception ... "+ex.getMessage());
        }
    }

    private static void stergere(){
        try{
            show(()->{
                Artist[] artisti=artistClient.getAll();
                int id=artisti[artisti.length-1].getId();
                artistClient.delete(id);
                getAll("STERGERE");
            });
        }catch(RestClientException ex){
            System.out.println("Exception ... "+ex.getMessage());
        }
    }
    private static void actualizare(){
        try{
            show(()->{
                Artist[] artisti=artistClient.getAll();
                Artist artist=artisti[artisti.length-1];
                artist.setNumeArtist("AltNume");
                artist.setLocatie("AltaLocatie");
                artistClient.update(artist);
                getAll("\n\nUPDATE:");
            });
        }catch(RestClientException ex){
            System.out.println("Exception ... "+ex.getMessage());
        }
    }
}
