package pt.ulusofona.aed.rockindeisi2023;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Testes {
    ArrayList<Songs> songs = Main.getObjects(TipoEntidade.TEMA);
    ArrayList<SongArtists> artists = Main.getObjects(TipoEntidade.ARTISTA);
    ArrayList<InputInvalido> inputs = Main.getObjects(TipoEntidade.INPUT_INVALIDO);
    boolean test = Main.reset();

    @Test
    public void testeAnoMenor1995() {
        for (Songs song : songs) {
            if (song.ano < 1995) {
                String resultado = song.toString();
                assertEquals(resultado, song.toString());
            }
        }
    }

    @Test
    public void testeAnoEntre1995e2000() {
        for (Songs song : songs) {
            if (song.ano >= 1995 && song.ano < 2000) {
                String resultado = song.toString();
                assertEquals(resultado, song.toString());
            }
        }
    }

    @Test
    public void testeAnoMaior2000() {
        for (Songs song : songs) {
            if (song.ano >= 2000) {
                String resultado = song.toString();
                assertEquals(resultado, song.toString());
            }
        }
    }

    @Test
    public void testeArtistaInicialA() {
        for (SongArtists artist : artists) {
            if (artist.nome.charAt(0) <= 'D') {
                String resultado = artist.toString();
                assertEquals(resultado, artist.toString());
            }
        }
    }

    @Test
    public void testeArtistaInicialMaiorD() {
        for (SongArtists artist : artists) {
            if (artist.nome.charAt(0) > 'D') {
                String resultado = artist.toString();
                assertEquals(resultado, artist.toString());
            }
        }
    }

    @Test
    public void testeINputInvalido() {
        for (InputInvalido input : inputs) {
            String resultado = input.toString();
            assertEquals(resultado, input.toString());
        }
    }

    @Test
    public void testCreativeQuery() {

        Main.loadFiles(new File("test-files/creativeQuey"));
        QueryResult queryResult = Main.execute("GET_LONGEST_SONGS_YEAR 1991");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(5, resultParts.length);
        assertArrayEquals(new String[] {
                "song4 | 16:23",
                "song5 | 11:12",
                "song1 | 5:49",
                "song2 | 4:53",
                "song3 | 3:58"
        }, resultParts);



    }

    @Test
    public void testCreativeQuery2() {
        Main.loadFiles(new File("test-files/creativeQuey2"));
        QueryResult queryResult = Main.execute("GET_LONGEST_SONGS_YEAR 1992");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(3, resultParts.length);
        assertArrayEquals(new String[] {
                "song2 | 3:13",
                "song5 | 2:52",
                "song1 | 2:29"
        }, resultParts);


    }

    @Test
    public void testcountSongsYear() {
        Main.loadFiles(new File("test-files/countSongsYear"));
        QueryResult queryResult = Main.execute("COUNT_SONGS_YEAR 1992");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        assertEquals(2,Integer.parseInt(queryResult.result));

        queryResult = Main.execute("COUNT_SONGS_YEAR 1991");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        assertEquals(3,Integer.parseInt(queryResult.result));
    }

    @Test
    public void testgetMostDanceable() {
        Main.loadFiles(new File("test-files/getMostDanceable"));
        QueryResult queryResult = Main.execute("GET_MOST_DANCEABLE 1991 1992 5");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(5, resultParts.length);
        assertArrayEquals(new String[] {
                "song5 : 1991 : 0.923",
                "song1 : 1991 : 0.912",
                "song3 : 1991 : 0.798",
                "song4 : 1992 : 0.612",
                "song2 : 1992 : 0.372"
        }, resultParts);
    }
    @Test
    public void testaddTagsSimple() {
        Main.loadFiles(new File("test-files/addTags"));
        QueryResult queryResult = Main.execute("ADD_TAGS Queen;Rock");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        assertEquals("Queen | ROCK", queryResult.result);

    }

    @Test
    public void testaddTagsComplex() {

        Main.loadFiles(new File("test-files/addTags"));
        QueryResult queryResult = Main.execute("ADD_TAGS Juice WRLD;Hip-Hop;Rap");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        assertEquals("Juice WRLD | HIP-HOP,RAP", queryResult.result);


    }

    @Test
    public void testremoveTagsSimple() {
        Main.loadFiles(new File("test-files/removeTags"));
        Main.execute("ADD_TAGS Queen;Rock;Pop");
        QueryResult queryResult = Main.execute("REMOVE_TAGS Queen;Pop");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        assertEquals("Queen | ROCK", queryResult.result);


    }

    @Test
    public void testremoveTagsComplex() {

        Main.loadFiles(new File("test-files/removeTags"));
        Main.execute("ADD_TAGS Juice WRLD;Rap;Hip-Hop");
        QueryResult queryResult = Main.execute("REMOVE_TAGS Juice WRLD;Hip-Hop;Rap");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        assertEquals("Juice WRLD | No tags", queryResult.result);
    }

    @Test
    public void testgetUniqueTags() {
        Main.loadFiles(new File("test-files/getUniqueTags"));
        Main.execute("ADD_TAGS Queen;Rock");
        Main.execute("ADD_TAGS Nirvana;Rock;Pop");
        Main.execute("ADD_TAGS Juice WRLD;Rock;Pop;Rap");
        QueryResult queryResult = Main.execute("GET_UNIQUE_TAGS");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(3, resultParts.length);
        assertArrayEquals(new String[] {
                "RAP 1",
                "POP 2",
                "ROCK 3",
        }, resultParts);
    }




    @Test
    public void testgetSongsByArtist() {
        Main.loadFiles(new File("test-files/getSongsByArtist"));
        QueryResult queryResult = Main.execute("GET_SONGS_BY_ARTIST 3 Queen");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(3, resultParts.length);
        assertArrayEquals(new String[] {
                "song1 : 1991",
                "song3 : 1992",
                "song5 : 1997",
        }, resultParts);
        queryResult = Main.execute("GET_SONGS_BY_ARTIST 1 Nirvana");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        resultParts = queryResult.result.split("\n");
        assertEquals(1, resultParts.length);
        assertArrayEquals(new String[] {
                "song2 : 1991"
        }, resultParts);
    }



    @Test
    public void parseMultipleArtistsSimple() {
        assertArrayEquals(new String[] { "aaa" },
                Main.parseMultipleArtists("['aaa']").toArray());
        assertArrayEquals(new String[] { "aaa", "bbb" },
                Main.parseMultipleArtists("['aaa', 'bbb']").toArray());



}

    @Test
    public void parseMultipleArtistsSpaceFormat() {

        assertArrayEquals(new String[] { "a,aa", "bbb" },
                Main.parseMultipleArtists("['a,aa', 'bbb']").toArray());
        assertArrayEquals(new String[] { "aaa", "bb b" },
                Main.parseMultipleArtists("['aaa', 'bb b']").toArray());

    }

    @Test
    public void parseMultipleArtistsQuotes() {

        assertArrayEquals(new String[] { "a'aa", "bbb" },
                Main.parseMultipleArtists("[\"\"a'aa\"\", 'bbb']").toArray());
        assertArrayEquals(new String[] { "a' aa", "b, bb", "ccc" },
                Main.parseMultipleArtists("[\"\"a' aa\"\", 'b, bb', 'ccc']").toArray());

    }


    @Test
    public void parseMultipleArtistsComplex() {

        assertArrayEquals(new String[] { "Engelbert Humperdinck", "Herbert von Karajan",
                        "Bancroft's School Choir", "Elisabeth Grümmer", "Elisabeth Schwarzkopf",
                        "Loughton High School for Girls' Choir", "Philharmonia Orchestra" },
                Main.parseMultipleArtists("['Engelbert Humperdinck', 'Herbert von Karajan', \"\"Bancroft's School Choir\"\"," +
                        " 'Elisabeth Grümmer', 'Elisabeth Schwarzkopf'," +
                        " \"\"Loughton High School for Girls' Choir\"\", 'Philharmonia Orchestra']").toArray());
        assertArrayEquals(new String[]{"Lin-Manuel Miranda", "'In The Heights' Original Broadway Company"},
                Main.parseMultipleArtists("['Lin-Manuel Miranda', \"\"'In The Heights' Original Broadway Company\"\"]").toArray());

    }





}


