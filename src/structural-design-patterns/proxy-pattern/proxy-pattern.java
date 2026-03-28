import java.util.HashMap;
import java.util.Map;

interface VideoDownloader {
    String downloadVideo(String videoURL);
}

class YouTubeDownloader implements VideoDownloader {
    @Override
    public String downloadVideo(String videoURL) {
        // Simulate downloading video from YouTube
        return "YouTube video downloaded from: " + videoURL;
    }
}

class VideoDownloaderProxy implements VideoDownloader {
    private YouTubeDownloader youTubeDownloader;
    private Map<String, String> cache; // Cache to store downloaded videos

    public VideoDownloaderProxy() {
        this.youTubeDownloader = new YouTubeDownloader();
        this.cache = new HashMap<>();
    }

    @Override
    public String downloadVideo(String videoURL) {
        if (cache.containsKey(videoURL)) {
            System.out.println("Returning cached video for: " + videoURL);
            return cache.get(videoURL);
        }
        // Additional logic can be added here (e.g., caching, access control)
        String video = youTubeDownloader.downloadVideo(videoURL);
        cache.put(videoURL, video);
        return video;
    }
}

class Main {
    public static void main(String[] args) {
        VideoDownloader videoDownloader = new VideoDownloaderProxy();

        // First download (not cached)
        System.out.println(videoDownloader.downloadVideo("https://youtube.com/video1"));

        // Second download (cached)
        System.out.println(videoDownloader.downloadVideo("https://youtube.com/video1"));
    }
}