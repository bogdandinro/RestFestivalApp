package festival.restClient;

import festival.model.Artist;
import festival.restService.ServiceException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class ArtistClient {
    public static final String URL = "http://localhost:8080/festival/artist";

    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Artist[] getAll() {
        return execute(() -> restTemplate.getForObject(URL, Artist[].class));
    }

    public Artist getById(Integer id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL, id), Artist.class));
    }

    public Artist create(Artist artist) {
        return execute(() -> restTemplate.postForObject(URL, artist, Artist.class));
    }

    public void update(Artist artist) {
        execute(() -> {
            restTemplate.put(String.format("%s/%s", URL, artist.getId()), artist);
            return null;
        });
    }

    public void delete(Integer id) {
        execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL, id));
            return null;
        });
    }
}
