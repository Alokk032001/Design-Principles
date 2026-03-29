interface videoQuality {
    void playVideo(String videoName);
}

class SDQuality implements videoQuality {
    @Override
    public void playVideo(String videoName) {
        System.out.println("Playing " + videoName + " in SD quality.");
    }
}

class HDQuality implements videoQuality {
    @Override
    public void playVideo(String videoName) {
        System.out.println("Playing " + videoName + " in HD quality.");
    }
}

class ultraHDQuality implements videoQuality {
    @Override
    public void playVideo(String videoName) {
        System.out.println("Playing " + videoName + " in Ultra HD quality.");
    }
}

abstract class VideoPlayer {
    protected videoQuality quality;

    public VideoPlayer(videoQuality quality) {
        this.quality = quality;
    }

    public abstract void play(String videoName);
}

class MobileVideoPlayer extends VideoPlayer {
    public MobileVideoPlayer(videoQuality quality) {
        super(quality);
    }

    @Override
    public void play(String videoName) {
        System.out.println("Mobile Video Player:");
        quality.playVideo(videoName);
    }
}

class DesktopVideoPlayer extends VideoPlayer {
    public DesktopVideoPlayer(videoQuality quality) {
        super(quality);
    }

    @Override
    public void play(String videoName) {
        System.out.println("Desktop Video Player:");
        quality.playVideo(videoName);
    }
}

class Main {
    public static void main(String[] args) {
        VideoPlayer mobilePlayer = new MobileVideoPlayer(new HDQuality());
        mobilePlayer.play("Nature Documentary");

        VideoPlayer desktopPlayer = new DesktopVideoPlayer(new ultraHDQuality());
        desktopPlayer.play("Action Movie");
    }
}