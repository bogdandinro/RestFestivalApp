package festival.restService;

import festival.model.Artist;
import festival.persistance.RepoArtist;
import festival.persistance.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/festival/artist")
public class FestivalArtistController {

    @Autowired
    private RepoArtist repo;

    @RequestMapping(method = RequestMethod.GET)
    public Artist[] getAll(){
        return repo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id){

        Artist artist = repo.findOne(id);
        if (artist == null)
            return new ResponseEntity<String>("Artist not found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Artist>(artist, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Artist save(@RequestBody Artist artist){
        repo.save(artist);
        return artist;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Artist update(@RequestBody Artist artist) {
        System.out.println("Updating artist ...");
        repo.update(artist.getId(), artist);
        return artist;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        System.out.println("Deleting artist ... " + id);
        try {
            repo.delete(id);
            return new ResponseEntity<Artist>(HttpStatus.OK);
        }catch (RepositoryException ex){
            System.out.println("Controller delete artist exception");
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
