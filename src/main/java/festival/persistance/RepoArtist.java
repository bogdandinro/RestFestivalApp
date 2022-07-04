package festival.persistance;
import festival.model.Artist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

@Component
public class RepoArtist implements IRepoArtist {
    private static final String url="jdbc:sqlite:E:\\Facultate\\Prelungire\\MPP\\Lab2022\\Lab7REST\\Festival.db";

    private static final Logger logger= LogManager.getLogger(RepoArtist.class);
    private Connection instance = null;

    public RepoArtist() {
    }

    private Connection getNewConnection(){
        logger.info("trying to connect to database ... {}",url);
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error getting connection "+ e);
        }
        return con;
    }

    public Connection getConnection(){
        logger.traceEntry();
        try {
            if (instance == null || instance.isClosed())
                instance = getNewConnection();

        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(instance);
        return instance;
    }

    @Override
    public int save(Artist entity) {
        logger.info("Save RepoSpectacol");
        String insert="insert into Artist(numeArtist, locuriDisponibile, locuriVandute, locatie,dataSpectacol,oraSpectacol) values (?,?,?,?,?,?)";
        Connection con = getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(insert)) {
            pstmt.setString(1, entity.getNumeArtist());
            pstmt.setInt(2, entity.getLocuriDisponibile());
            pstmt.setInt(3, entity.getLocuriVandute());
            pstmt.setString(4, entity.getLocatie());
            pstmt.setString(5,entity.getData());
            pstmt.setString(6,entity.getOra());
            int res=pstmt.executeUpdate();
            pstmt.close();
            return res;
        } catch (SQLException e) {
            System.out.println("Insert error "+e);
            return -1;
        }

    }

    @Override
    public int delete(Integer id) {
        String delete="delete from Artist where ID=?";
        Connection con =getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(delete)) {
            pstmt.setInt(1, id);
            int res= pstmt.executeUpdate();
            pstmt.close();
            return res;
        } catch (SQLException e) {
            System.out.println("Delete error "+e);
            return -1;
        }
    }

    @Override
    public int update(Integer id, Artist entity) {
        String delete="update Artist set numeArtist=?,locuriDisponibile=?,locuriVandute=?,locatie=?,dataSpectacol=?,oraSpectacol=? where ID=?";
        Connection con = getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(delete)) {
            pstmt.setString(1, entity.getNumeArtist());
            pstmt.setInt(2, entity.getLocuriDisponibile());
            pstmt.setInt(3, entity.getLocuriVandute());
            pstmt.setString(4, entity.getLocatie());
            pstmt.setString(5,entity.getData());
            pstmt.setString(6,entity.getOra());
            pstmt.setInt(7, id);
            int res=pstmt.executeUpdate();
            pstmt.close();
            return res;
        } catch (SQLException e) {
            System.out.println("Update error "+e);
            return -1;
        }
    }

    @Override
    public Artist findOne(Integer ID) {
        Connection con = getConnection();
        try {
            String select="select * from Artist where id=?";
            PreparedStatement stmt  = con.prepareStatement(select);
            stmt.setInt(1,ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nume = rs.getString("NumeArtist");
                int locuriDisp = rs.getInt("LocuriDisponibile");
                int locuriVandute = rs.getInt("LocuriVandute");
                String locatie = rs.getString("Locatie");
                String data = rs.getString("DataSpectacol");
                String ora=rs.getString("OraSpectacol");
                Artist spect = new Artist(nume, locatie, locuriDisp, locuriVandute, data,ora);
                spect.setId(id);
                return spect;
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Artist[] findAll() {
        TreeMap<Integer, Artist> all=new TreeMap<>();
        Connection con = getConnection();
        try {
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery("select * from Artist");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nume = rs.getString("NumeArtist");
                int locuriDisp = rs.getInt("LocuriDisponibile");
                int locuriVandute = rs.getInt("LocuriVandute");
                String locatie = rs.getString("Locatie");
                String data = rs.getString("DataSpectacol");
                String ora=rs.getString("OraSpectacol");
                Artist spect = new Artist(nume, locatie, locuriDisp, locuriVandute, data,ora);
                spect.setId(id);
                all.put(spect.getId(),spect);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all.values().toArray(new Artist[all.size()]);
    }
}
