package pt.ulusofona.aed.rockindeisi2023;



import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    static LinkedHashMap<String, Songs> mapSongs = new LinkedHashMap<>();
    static LinkedHashMap<String, SongDetails> mapSongDetails = new LinkedHashMap<>();
    static LinkedHashMap<String, ArrayList<SongArtists>> mapSongArtists = new LinkedHashMap<>();
    static ArrayList<InputInvalido> arrayInputInvalido = new ArrayList<>();
    static LinkedHashMap<String,ArrayList<SongArtists>> mapFullArtists;

    public static boolean reset() {
        mapSongs.clear();
        return true;
    }
    public static String getLongestSongsYear(int year) {
        ArrayList<Songs> validSongs = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        for (Songs song : mapSongs.values()) {
            if (song.ano == year) {
                validSongs.add(song);
            }
        }

        validSongs.sort((s1,s2) -> Double.compare(s2.details.duracao, s1.details.duracao));

        int count = 0;
        for (Songs song : validSongs) {
            long segundos = song.details.duracao / 1000;
            long minutos = segundos / 60;
            long segundosR = segundos % 60;
            String duracaoFormatada = String.format("%d:%02d", minutos, segundosR);
            result.append(song.nome).append(" | ").append(duracaoFormatada).append("\n");
            count++;
            if (count == 5) {
                result.deleteCharAt(result.length() - 1);
                break;
            }
        }
        return result.toString();
    }
    public static int countSongsYear(int year) {
        int count = 0;
        for (Songs song : mapSongs.values()) {
            if (song.ano == year) {
                count++;
            }
        }
        return count;
    }
    public static int countDuplicateSongsYear(int year) {
        HashMap<String, Integer> songCounts = new HashMap<>();
        int count = 0;

        for (Songs song : mapSongs.values()) {
            if (song.ano == year) {
                String title = song.nome;
                songCounts.put(title, songCounts.getOrDefault(title, 0) + 1);
            }
        }

        for (int songCount : songCounts.values()) {
            if (songCount > 1) {
                count++;
            }
        }

        return count;
    }


    public static String getMostDanceable(int start, int end, int numResults) {
        ArrayList<Songs> filteredSongs = new ArrayList<>();

        for (Songs song : mapSongs.values()) {
            if (song.ano >= start && song.ano <= end) {
                filteredSongs.add(song);
            }
        }
        filteredSongs.sort((s1, s2) -> Double.compare(s2.details.dancabilidade, s1.details.dancabilidade));

        StringBuilder result = new StringBuilder();
        int count = 0;

        for (Songs song : filteredSongs) {
            result.append(song.nome).append(" : ").append(song.ano).
                    append(" : ").append(song.details.dancabilidade).append("\n");
            count++;
            if (count == numResults) {
                break;
            }
        }
        return result.toString();

    }


    public static String getArtistsOneSong(int start, int end) {
        ArrayList<String> artistsOneSong = new ArrayList<>();
        HashSet<String> linhasUnicas = new HashSet<>();

        for (ArrayList<SongArtists> artistsList : mapSongArtists.values()) {
            if (artistsList.size() == 1) {
                SongArtists artist = artistsList.get(0);
                Songs song = mapSongs.get(artist.id);
                if (song.ano >= start && song.ano <= end) {
                    String linha = artist.nome + " | " + song.nome + " | " + song.ano;
                    if (linhasUnicas.add(linha)) {
                        artistsOneSong.add(linha);
                    }
                }
            }
        }

        artistsOneSong.sort((o1, o2) -> {
            boolean isNum1 = Character.isDigit(o1.charAt(0));
            boolean isNum2 = Character.isDigit(o2.charAt(0));

            if (isNum1 && isNum2) {
                return o1.compareTo(o2);
            } else if (isNum1) {
                return -1;
            } else if (isNum2) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }
        });

        StringBuilder result = new StringBuilder();
        for (String artistOneSong : artistsOneSong) {
            result.append(artistOneSong).append("\n");
        }
        System.out.println(artistsOneSong.size());

        return result.toString();
    }
    public static String getSongsByArtist(int numResults, String nameArtist) {

        List<String> songsByArtist = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (Map.Entry<String, Songs> songEntry : mapSongs.entrySet()) {
            String songId = songEntry.getKey();
            Songs song = songEntry.getValue();
            boolean artistFound = false;

            if (mapFullArtists.containsKey(songId)) {
                for (SongArtists artist : mapFullArtists.get(songId)) {
                    if (artist.nome.equals(nameArtist)) {
                        artistFound = true;
                        break;
                    }
                }
            }
            if (artistFound) {
                songsByArtist.add(song.nome + " : " + song.ano);
            }
        }

        if (songsByArtist.isEmpty()) {
            return "No songs";
        }

        for (String song : songsByArtist) {
            result.append(song).append("\n");
            count++;
            if (count == numResults) {
                break;
            }
        }

        return result.toString();
    }



    public static String addTags(String nameArtist, String... tags) {
        boolean artistFound = false;
        StringBuilder result = new StringBuilder();

        for (ArrayList<SongArtists> artists : mapSongArtists.values()) {
            for (SongArtists artist : artists) {
                if (artist.nome.equals(nameArtist)) {
                    if (!artistFound) {
                        artistFound = true;
                        for (String tag : tags) {
                            String upperCaseTag = tag.toUpperCase();
                            if (!artist.tags.contains(upperCaseTag)) {
                                artist.tags.add(upperCaseTag.trim());
                            }
                        }
                        result.append(artist.nome).append(" | ");
                        for (String tag : artist.tags) {
                            result.append(tag).append(",");
                        }
                        result.deleteCharAt(result.length() - 1);
                    }
                }
            }
        }

        if (!artistFound) {
            return "Inexistent artist";
        }

        return result.toString();
    }
    public static String removeTags(String nameArtist, String... tags) {

        boolean artistFound = false;
        StringBuilder result = new StringBuilder();

        for (ArrayList<SongArtists> artists : mapSongArtists.values()) {
            for (SongArtists artist : artists) {
                if (artist.nome.equals(nameArtist)) {
                    if (!artistFound) {
                        artistFound = true;
                        for (String tag : tags) {
                            String upperCaseTag = tag.toUpperCase();
                            if (artist.tags.contains(upperCaseTag)) {
                                artist.tags.remove(upperCaseTag.trim());
                            }
                        }
                        result.append(artist.nome).append(" | ");

                        if (!artist.tags.isEmpty()) {
                            for (String tag : artist.tags) {
                                result.append(tag).append(",");
                            }
                            result.deleteCharAt(result.length() - 1);
                        } else {
                            result.append("No tags");
                        }

                    }
                }
            }
        }

        if (!artistFound) {
            return "Inexistent artist";
        }

        return result.toString();

    }
    public static String getUniqueTags() {
        HashMap<String, Integer> tagCounts = new HashMap<>();

        for (ArrayList<SongArtists> artistsList : mapFullArtists.values()) {
            for (SongArtists artist : artistsList) {
                for (String tag : artist.tags) {
                    tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
                }
            }
        }

        if (tagCounts.isEmpty()) {
            return "No results";
        }

        StringBuilder result = new StringBuilder();
        ArrayList<Map.Entry<String, Integer>> sortedTags = new ArrayList<>(tagCounts.entrySet());
        sortedTags.sort(Map.Entry.comparingByValue());

        for (Map.Entry<String, Integer> tags : sortedTags) {
            String tag = tags.getKey();
            int count = tags.getValue();
            result.append(tag).append(" ").append(count).append("\n");
        }

        return result.toString();
    }
    public static String getArtistsForTag(String tag) {
        StringBuilder result = new StringBuilder();
        ArrayList<String> artistsForTag = new ArrayList<>();
        tag = tag.toUpperCase();

        for (ArrayList<SongArtists> artists : mapFullArtists.values()) {
            for (SongArtists artist : artists) {
                if (artist.tags.contains(tag)) {
                    artistsForTag.add(artist.nome);
                }
            }
        }
        Collections.sort(artistsForTag);

        if (artistsForTag.isEmpty()) {
            return "No results";
        }
        for (String linha : artistsForTag) {
            result.append(linha).append(";");
        }
        result.deleteCharAt(result.length() - 1);


        return result.toString();


    }

    public static String getUniqueTagsInBetweenYears(int start, int end) {
        LinkedHashMap<String, Integer> tagCounts = new LinkedHashMap<>();
        StringBuilder result = new StringBuilder();

        for (ArrayList<SongArtists> artistsList : mapFullArtists.values()) {
            for (SongArtists artist : artistsList) {
                for (String tag : artist.tags) {
                    Songs song = mapSongs.get(artist.id);
                    if (song != null && song.ano >= start && song.ano <= end) {
                        tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
                    }
                }
            }
        }

        if (tagCounts.isEmpty()) {
            return "No results";
        }


        ArrayList<Map.Entry<String, Integer>> sortedTags = new ArrayList<>(tagCounts.entrySet());
        sortedTags.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (Map.Entry<String, Integer> tags : sortedTags) {
            String tag = tags.getKey();
            int count = tags.getValue();
            result.append(tag).append(" ").append(count).append("\n");
        }

        return result.toString();
    }



    public static String getTopArtistsWithSongsBetween(int numArtists, int min, int max) {
        LinkedHashMap<String, Integer> artistCounts = new LinkedHashMap<>();
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (ArrayList<SongArtists> artistsList : mapFullArtists.values()) {
            for (SongArtists artist : artistsList) {
                if (mapSongs.containsKey(artist.id)) {
                    int songCount = artistCounts.getOrDefault(artist.nome, 0);
                    artistCounts.put(artist.nome, songCount + 1);
                }
            }
        }

        ArrayList<Map.Entry<String, Integer>> sortedArtists = new ArrayList<>(artistCounts.entrySet());
        sortedArtists.sort((a1, a2) -> Integer.compare(a2.getValue(), a1.getValue()));



        for (Map.Entry<String, Integer> artists : sortedArtists) {
            String artistName = artists.getKey();
            int songCount = artists.getValue();

            if (songCount >= min && songCount <= max) {
                result.append(artistName).append(" ").append(songCount).append("\n");
                count++;
                if (count == numArtists) {
                    break;
                }
            }
        }

        if (result.length() == 0) {
            return "No results";
        }

        return result.toString();
    }

    public static String mostFrequentWordsInArtistName(int numResults, int min, int size) {
        HashMap<String, Integer> wordCounts = new HashMap<>();
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (ArrayList<SongArtists> artists : mapFullArtists.values()) {
            for (SongArtists artist : artists) {
                String[] words = artist.nome.split(" ");
                for (String word : words) {
                    if (word.length() >= size) {
                        wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }

        ArrayList<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordCounts.entrySet());
        sortedWords.sort(Map.Entry.comparingByValue());


        for (Map.Entry<String, Integer> words : sortedWords) {
            String word = words.getKey();
            int occurrences = words.getValue();
            if (occurrences >= min) {
                result.append(word).append(" ").append(occurrences).append("\n");
                count++;
                if (count == numResults) {
                    break;
                }
            }
        }

        return result.toString();
    }

    public static boolean loadFiles(File folder) {

        mapSongs.clear();
        mapSongArtists.clear();
        mapSongDetails.clear();
        arrayInputInvalido.clear();
        if (mapFullArtists != null) {
            mapFullArtists.clear();
        }

        int LinhasOK = 0;
        int LinhasNOK = 0;
        int PrimeiraLinhaNOK = -1;

        File ficheiroSongs = new File(folder,"songs.txt");
        File ficheiroSongArtists = new File(folder,"song_artists.txt");
        File ficheiroSongDetails = new File(folder,"song_details.txt");

        HashSet<String> idsComDetalhes = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ficheiroSongDetails))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(" @ ");
                if (partes.length == 7) {
                    String id = partes[0];
                    int duracao = Integer.parseInt(partes[1].trim());
                    int letraexplicita = Integer.parseInt(partes[2].trim());
                    int popularidade = Integer.parseInt(partes[3].trim());
                    double dancabilidade = Double.parseDouble(partes[4].trim());
                    double vivacidade = Double.parseDouble(partes[5].trim());
                    double volumeMedio = Double.parseDouble(partes[6].trim());

                    SongDetails songDetails = new SongDetails(id, duracao, letraexplicita,
                            popularidade, dancabilidade, vivacidade, volumeMedio);
                    mapSongDetails.put(id, songDetails);
                    idsComDetalhes.add(id);

                    LinhasOK++;
                } else {
                    LinhasNOK++;

                    if (LinhasNOK == 1) {
                        PrimeiraLinhaNOK = LinhasOK + 1;
                    }
                }
            }
            InputInvalido ErroDetectado_SongDetails = new InputInvalido("song_details", LinhasOK, LinhasNOK, PrimeiraLinhaNOK);
            arrayInputInvalido.add(ErroDetectado_SongDetails);


        } catch (IOException e) {
            return false;
        }

        LinhasOK = 0;
        LinhasNOK = 0;
        PrimeiraLinhaNOK = -1;
        HashSet<String> idsEncontrados = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ficheiroSongs))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("@");
                if (partes.length == 3) {
                    String id = partes[0].trim();
                    if (!idsEncontrados.contains(id)) {
                        idsEncontrados.add(id);
                        if (idsComDetalhes.contains(id)) {

                            String titulo = partes[1].trim();
                            int ano = Integer.parseInt(partes[2].trim());
                            Songs songs = new Songs(id, titulo, ano);
                            mapSongs.put(id, songs);
                        }

                    } else {
                        LinhasNOK++;
                        if (LinhasNOK == 1) {
                            PrimeiraLinhaNOK = LinhasOK + 1;
                        }
                        LinhasOK--;
                    }
                    LinhasOK++;
                } else {
                    LinhasNOK++;
                    if (LinhasNOK == 1) {
                        PrimeiraLinhaNOK = LinhasOK + 1;
                    }
                }
            }

            InputInvalido ErroDetectado_Songs = new InputInvalido("songs", LinhasOK, LinhasNOK, PrimeiraLinhaNOK);
            arrayInputInvalido.add(0, ErroDetectado_Songs);


            for (Map.Entry<String, Songs> songEntry : mapSongs.entrySet()) {
                String id = songEntry.getKey();
                Songs song = songEntry.getValue();
                SongDetails detail = mapSongDetails.get(id);
                if (detail != null) {
                    song.details = detail;
                }
            }


        } catch (IOException e) {
            return false;
        }
        HashSet<String> songIds = new HashSet<>(mapSongs.keySet());
        HashMap<String, Integer> artistSongCount = new HashMap<>();
        ArrayList<SongArtists> existingArtists;
        ArrayList<String> artistasList;
        LinhasOK = 0;
        LinhasNOK = 0;
        PrimeiraLinhaNOK = -1;
        try (BufferedReader reader = new BufferedReader(new FileReader(ficheiroSongArtists))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("@");
                if (partes.length == 2) {
                    String id = partes[0].trim();
                    partes[1] = partes[1].trim();
                    if (songIds.contains(id)) {
                        String linhaArt = partes[1];
                        artistasList = parseMultipleArtists(linhaArt);
                        if (artistasList.size() > 1) {
                            if (linhaArt.startsWith("['")) {
                                LinhasNOK++;
                                if (LinhasNOK == 1) {
                                    PrimeiraLinhaNOK = LinhasOK + 1;
                                }
                                LinhasOK--;
                            }
                        }
                        if (!artistasList.isEmpty()) {
                                    for (String artista : artistasList) {
                                        if (!artista.isEmpty()) {
                                            SongArtists songArtists = new SongArtists(id, artista);
                                            existingArtists = mapSongArtists.getOrDefault(id, new ArrayList<>());
                                            boolean artistExist = false;
                                            for (SongArtists existing : existingArtists) {
                                                if (existing.nome.equals(songArtists.nome)) {
                                                    artistExist = true;
                                                    break;
                                                }
                                            }
                                            if (!artistExist) {
                                                existingArtists.add(songArtists);
                                                artistSongCount.put(artista, artistSongCount.getOrDefault(artista, 0) + 1);
                                            }
                                            mapSongArtists.put(id, existingArtists);
                                        }

                            }
                        }
                    } else {

                        LinhasNOK++;
                        if (LinhasNOK == 1) {
                            PrimeiraLinhaNOK = LinhasOK + 1;
                        }
                        LinhasOK--;
                    }
                    LinhasOK++;

                } else {
                    LinhasNOK++;
                    if (LinhasNOK == 1) {
                        PrimeiraLinhaNOK = LinhasOK + 1;
                    }
                }
            }
            InputInvalido ErroDetectado_SongArtists = new InputInvalido("song_artists", LinhasOK, LinhasNOK, PrimeiraLinhaNOK);
            arrayInputInvalido.add(ErroDetectado_SongArtists);

        } catch (IOException e) {
            return false;
        }


        for (Map.Entry<String, ArrayList<SongArtists>> artistEntry : mapSongArtists.entrySet()) {
            String id = artistEntry.getKey();
            ArrayList<SongArtists> artistsList = artistEntry.getValue();

            if (songIds.contains(id)) {
                for (SongArtists artist : artistsList) {
                    Songs song = mapSongs.get(id);
                    if (song.artists != null) {
                        if (!song.artists.contains(artist)) {
                            song.artists.add(artist);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, ArrayList<SongArtists>> artistEntry : mapSongArtists.entrySet()) {
            String id = artistEntry.getKey();
            ArrayList<SongArtists> artistsList = artistEntry.getValue();

            if (songIds.contains(id)) {
                for (SongArtists artist : artistsList) {
                    artist.numMusicas = artistSongCount.getOrDefault(artist.nome, 0);
                }
            }
        }

        mapFullArtists = (LinkedHashMap<String, ArrayList<SongArtists>>) mapSongArtists.clone();
        removeSongsWithoutArtist();
        removeDuplicateArtists();

        return true;
    }

    public static void removeDuplicateArtists() {
        Set<String> visitedArtists = new HashSet<>();
        Iterator<Map.Entry<String, ArrayList<SongArtists>>> iterator = mapSongArtists.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, ArrayList<SongArtists>> entry = iterator.next();
            ArrayList<SongArtists> artistsList = entry.getValue();

            ArrayList<SongArtists> uniqueArtists = new ArrayList<>();

            for (SongArtists artist : artistsList) {
                if (!visitedArtists.contains(artist.nome)) {
                    uniqueArtists.add(artist);
                    visitedArtists.add(artist.nome);
                }
            }

            if (uniqueArtists.isEmpty()) {
                iterator.remove();
            } else {
                entry.setValue(uniqueArtists);
            }
        }
    }

    public static void removeSongsWithoutArtist() {
        ArrayList<String> songsToRemove = new ArrayList<>();

        for (String songId : mapSongs.keySet()) {
            if (!mapSongArtists.containsKey(songId)) {
                songsToRemove.add(songId);
            }
        }

        for (String songId : songsToRemove) {
            mapSongs.remove(songId);
        }
    }



    public static ArrayList<String> parseMultipleArtists(String str) {
        ArrayList<String> result = new ArrayList<>();

        String cleanInput = str.trim().substring(1, str.length() - 1);
        Pattern pattern = Pattern.compile("\"\"(.*?)\"\"|'(.*?)'");

        Matcher matcher = pattern.matcher(cleanInput);
        while (matcher.find()) {
            String match = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
            result.add(match);
        }
        return result;
    }


    public static ArrayList getObjects(TipoEntidade tipo) {
        ArrayList objetos = new ArrayList<>();

        switch (tipo) {
            case TEMA:
                objetos.addAll(mapSongs.values());
                break;
            case ARTISTA:
                for (ArrayList<SongArtists> artists : mapSongArtists.values()) {
                    objetos.addAll(artists);
                }
                break;
            case INPUT_INVALIDO:
                objetos.addAll(arrayInputInvalido);
                break;
        }
        return objetos;
    }



    public static Query parseCommand(String command) {
        String[] parts = command.trim().split("\\s+", 2); // Divide o comando em duas partes: nome e argumentos
        String name = parts[0].toUpperCase(); // Obtém o nome da query em letras maiúsculas

        if (parts.length == 2) {
            String[] args = parts[1].split("\\s+"); // Divide os argumentos em strings individuais

            // Verifica se o nome da query é válido
            if (name.equals("COUNT_SONGS_YEAR") || name.equals("GET_SONGS_BY_ARTIST") ||
                    name.equals("GET_MOST_DANCEABLE") || name.equals("MOST_FREQUENT_WORDS_IN_ARTIST_NAME") ||
                    name.equals("ADD_TAGS") || name.equals("REMOVE_TAGS") || name.equals("GET_ARTISTS_FOR_TAG") ||
                    name.equals("COUNT_DUPLICATE_SONGS_YEAR") || name.equals("GET_ARTISTS_ONE_SONG")||
                    name.equals("GET_TOP_ARTISTS_WITH_SONGS_BETWEEN") ||
                    name.equals("GET_UNIQUE_TAGS_IN_BETWEEN_YEARS") || name.equals("GET_RISING_STARS") ||
            name.equals("GET_LONGEST_SONGS_YEAR")) {

                if (name.equals("GET_SONGS_BY_ARTIST")) {
                    StringBuilder concatenated = new StringBuilder(args[1]);

                    for (int i = 2; i < args.length; i++) {
                        concatenated.append(" ").append(args[i]);
                    }

                    args = new String[]{args[0], concatenated.toString()};
                }
                if (name.equals("ADD_TAGS") || name.equals("REMOVE_TAGS")) {
                    args = parts[1].split(";");
                }


                return new Query(name, args);
            }


        } else if (parts.length == 1) {
            if (name.equals("GET_UNIQUE_TAGS")) {
                String[] args = new String[0];
                return new Query(name, args);
            }
        }

        return null; // Comando inválido, retorna null
    }



    static QueryResult execute(String command) {
        String[] part = command.split(" ");
        String name = part[0];
        String[] args = new String[part.length - 1];
        Query query = new Query(name,args);
        long startTime;
        long endTime;

        switch (query.name) {
            case "COUNT_SONGS_YEAR":
                int ano = Integer.parseInt(part[1].trim());
                startTime = System.currentTimeMillis();
                int nums = countSongsYear(ano);
                endTime = System.currentTimeMillis();
                return new QueryResult(String.valueOf(nums),endTime - startTime);
            case "GET_SONGS_BY_ARTIST":
                for (int i = 3; i < part.length; i++) {
                    part[2] += " " + part[i];
                }
                startTime = System.currentTimeMillis();
                String idsArtist = getSongsByArtist(Integer.parseInt(part[1]),part[2]);
                endTime = System.currentTimeMillis();
                return new QueryResult(idsArtist,endTime - startTime);
            case "COUNT_DUPLICATE_SONGS_YEAR":
                int year = Integer.parseInt(part[1].trim());
                startTime = System.currentTimeMillis();
                String result = String.valueOf(countDuplicateSongsYear(year));
                endTime = System.currentTimeMillis();
                return new QueryResult(result,endTime - startTime);
            case "GET_MOST_DANCEABLE":
                int yearStart = Integer.parseInt(part[1]);
                int yearEnd = Integer.parseInt(part[2]);
                int numResult = Integer.parseInt(part[3]);
                startTime = System.currentTimeMillis();
                result = getMostDanceable(yearStart,yearEnd,numResult);
                endTime = System.currentTimeMillis();
                return new QueryResult(result,endTime - startTime);
            case "GET_ARTISTS_ONE_SONG":
                int start = Integer.parseInt(part[1]);
                int end = Integer.parseInt(part[2]);
                startTime = System.currentTimeMillis();
                result = getArtistsOneSong(start,end);
                endTime = System.currentTimeMillis();
                return new QueryResult(result,endTime - startTime);
            case "ADD_TAGS":
                part = command.split(" ",2);
                String[] partTagsAdd = part[1].split(";");
                String artistName = partTagsAdd[0];
                String[] tags = Arrays.copyOfRange(partTagsAdd, 1, partTagsAdd.length);
                startTime = System.currentTimeMillis();
                result = addTags(artistName, tags);
                endTime = System.currentTimeMillis();
                return new QueryResult(result, endTime - startTime);
            case "REMOVE_TAGS":
                part = command.split(" ",2);
                String[] partTagsRemove = part[1].split(";");
                String artistNameR = partTagsRemove[0];
                String[] tagsR = Arrays.copyOfRange(partTagsRemove,1,partTagsRemove.length);
                startTime = System.currentTimeMillis();
                result = removeTags(artistNameR,tagsR);
                endTime = System.currentTimeMillis();
                return new QueryResult(result,endTime - startTime);
            case "GET_UNIQUE_TAGS":
                startTime = System.currentTimeMillis();
                String resTags = getUniqueTags();
                endTime = System.currentTimeMillis();
                return new QueryResult(resTags,endTime - startTime);
            case "GET_ARTISTS_FOR_TAG":
                String tag = part[1];
                startTime = System.currentTimeMillis();
                result = getArtistsForTag(tag);
                endTime = System.currentTimeMillis();
                return new QueryResult(result,endTime - startTime);
            case "GET_UNIQUE_TAGS_IN_BETWEEN_YEARS":
                int inicio = Integer.parseInt(part[1].trim());
                int fim = Integer.parseInt(part[2].trim());
                startTime = System.currentTimeMillis();
                result = getUniqueTagsInBetweenYears(inicio,fim);
                endTime = System.currentTimeMillis();
                return new QueryResult(result,endTime - startTime);
            case "MOST_FREQUENT_WORDS_IN_ARTIST_NAME":
                int numResults = Integer.parseInt(part[1].trim());
                int min = Integer.parseInt(part[2].trim());
                int size = Integer.parseInt(part[3].trim());
                startTime = System.currentTimeMillis();
                result = mostFrequentWordsInArtistName(numResults,min,size);
                endTime = System.currentTimeMillis();
                return new QueryResult(result,endTime - startTime);
            case "GET_TOP_ARTISTS_WITH_SONGS_BETWEEN":
                int numArtists = Integer.parseInt(part[1].trim());
                int minn = Integer.parseInt(part[2].trim());
                int max = Integer.parseInt(part[3].trim());
                startTime = System.currentTimeMillis();
                result = getTopArtistsWithSongsBetween(numArtists,minn,max);
                endTime = System.currentTimeMillis();
                return new QueryResult(result,endTime - startTime);
            case "GET_LONGEST_SONGS_YEAR":
                int yearr = Integer.parseInt(part[1].trim());
                startTime = System.currentTimeMillis();
                result = getLongestSongsYear(yearr);
                endTime = System.currentTimeMillis();
                return new QueryResult(result,endTime - startTime);
        }
        return null;
    }

    public static void main(String[] args) {


        System.out.println("Welcome to Rock in DEISI!");
        if (!loadFiles(new File("files"))) {
            System.out.println("Error reading files");
            return;
        }
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (line != null && !line.equals("EXIT")) {
            QueryResult result = execute(line);
            if (result == null || parseCommand(line) == null) {
                System.out.println("Illegal command. Try again");
            } else {
                System.out.println(result.result);
                System.out.println("(took " + result.time + " ms)");
            }
            line = in.nextLine();
        }

    }
}
