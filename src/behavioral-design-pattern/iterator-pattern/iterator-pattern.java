// ========== Video class representing a single video ==========

import java.util.ArrayList;
import java.util.List;

class Video {
    private String title;
    private String genre;
    private int duration; // in minutes

    public Video(String title, String genre, int duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }
}

interface Playlist {
    // Method to return an iterator for the collection
    PlaylistIterator createIterator();
}

// ========== YouTubePlaylist class ==========
class YouTubePlaylist implements Playlist {
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        videos.add(video);
    }

    @Override
    public PlaylistIterator createIterator() {
        return new YouTubePlaylistIterator(videos);
    }

    public List<Video> getVideos() {
        return videos;
    }
}

// ========== Iterator interface ==========
interface PlaylistIterator {
    boolean hasNext();

    Video next();
}

class YouTubePlaylistIterator implements PlaylistIterator {
    private List<Video> videos;
    private int currentIndex = 0;

    public YouTubePlaylistIterator(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < videos.size();
    }

    @Override
    public Video next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more videos in the playlist.");
        }
        return videos.get(currentIndex++);
    }
}

class Main {
    public static void main(String[] args) {
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("Java Design Patterns", "Education", 30));
        playlist.addVideo(new Video("Iterator Pattern Explained", "Education", 20));
        playlist.addVideo(new Video("Design Patterns in Java", "Education", 45));

        PlaylistIterator iterator = new YouTubePlaylistIterator(playlist.getVideos());

        System.out.println("YouTube Playlist:");
        while (iterator.hasNext()) {
            Video video = iterator.next();
            System.out
                    .println("- " + video.getTitle() + " (" + video.getGenre() + ", " + video.getDuration() + " mins)");
        }
    }
}