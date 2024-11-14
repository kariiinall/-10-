class AudioSystem {
    public void turnOn() {
        System.out.println("Аудиосистема включена.");
    }

    public void setVolume(int level) {
        System.out.println("Громкость аудиосистемы установлена на уровень " + level + ".");
    }

    public void turnOff() {
        System.out.println("Аудиосистема выключена.");
    }
}

class VideoProjector {
    public void turnOn() {
        System.out.println("Видеопроектор включен.");
    }

    public void setResolution(String resolution) {
        System.out.println("Разрешение видеопроектора установлено на " + resolution + ".");
    }

    public void turnOff() {
        System.out.println("Видеопроектор выключен.");
    }
}

class LightingSystem {
    public void turnOn() {
        System.out.println("Освещение включено.");
    }

    public void setBrightness(int level) {
        System.out.println("Яркость освещения установлена на уровень " + level + ".");
    }

    public void turnOff() {
        System.out.println("Освещение выключено.");
    }
}

class HomeTheaterFacade {
    private AudioSystem audioSystem;
    private VideoProjector videoProjector;
    private LightingSystem lightingSystem;

    public HomeTheaterFacade(AudioSystem audioSystem, VideoProjector videoProjector, LightingSystem lightingSystem) {
        this.audioSystem = audioSystem;
        this.videoProjector = videoProjector;
        this.lightingSystem = lightingSystem;
    }

    public void startMovie() {
        System.out.println("Подготовка к началу фильма...");
        lightingSystem.turnOn();
        lightingSystem.setBrightness(5);
        audioSystem.turnOn();
        audioSystem.setVolume(8);
        videoProjector.turnOn();
        videoProjector.setResolution("HD");
        System.out.println("Фильм начат.");
    }

    public void endMovie() {
        System.out.println("Завершение показа фильма...");
        videoProjector.turnOff();
        audioSystem.turnOff();
        lightingSystem.turnOff();
        System.out.println("Фильм завершен.");
    }
}

class HomeTheaterTest {
    public static void main(String[] args) {
        AudioSystem audio = new AudioSystem();
        VideoProjector video = new VideoProjector();
        LightingSystem lights = new LightingSystem();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(audio, video, lights);

        homeTheater.startMovie();
        System.out.println();

        homeTheater.endMovie();
    }
}
