class TV {
    public void on() {
        System.out.println("TV включен");
    }

    public void off() {
        System.out.println("TV выключен");
    }

    public void setChannel(int channel) {
        System.out.println("TV: установлен канал " + channel);
    }
}

class AudioSystem {
    public void on() {
        System.out.println("Аудиосистема включена");
    }

    public void off() {
        System.out.println("Аудиосистема выключена");
    }

    public void setVolume(int level) {
        System.out.println("Аудиосистема: установлена громкость " + level);
    }
}

class DVDPlayer {
    public void play() {
        System.out.println("DVD-проигрыватель: воспроизведение");
    }

    public void pause() {
        System.out.println("DVD-проигрыватель: пауза");
    }

    public void stop() {
        System.out.println("DVD-проигрыватель: остановка");
    }
}

class GameConsole {
    public void on() {
        System.out.println("Игровая консоль включена");
    }

    public void startGame(String game) {
        System.out.println("Игровая консоль: запущена игра " + game);
    }
}

class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audioSystem;
    private DVDPlayer dvdPlayer;
    private GameConsole gameConsole;

    public HomeTheaterFacade(TV tv, AudioSystem audioSystem, DVDPlayer dvdPlayer, GameConsole gameConsole) {
        this.tv = tv;
        this.audioSystem = audioSystem;
        this.dvdPlayer = dvdPlayer;
        this.gameConsole = gameConsole;
    }

    public void watchMovie() {
        System.out.println("Подготовка к просмотру фильма...");
        tv.on();
        audioSystem.on();
        dvdPlayer.play();
    }

    public void endMovie() {
        System.out.println("Завершение просмотра фильма...");
        dvdPlayer.stop();
        audioSystem.off();
        tv.off();
    }

    public void listenToMusic() {
        System.out.println("Подготовка к прослушиванию музыки...");
        tv.on();
        audioSystem.on();
        System.out.println("Аудиовход на TV установлен");
    }

    public void adjustVolume(int level) {
        audioSystem.setVolume(level);
    }

    public void playGame(String game) {
        System.out.println("Подготовка к игре...");
        tv.on();
        gameConsole.on();
        gameConsole.startGame(game);
    }

    public void turnOffSystem() {
        System.out.println("Выключение всей системы...");
        dvdPlayer.stop();
        audioSystem.off();
        tv.off();
        gameConsole.on();
    }
}

class HomeTheaterTest {
    public static void main(String[] args) {
        TV tv = new TV();
        AudioSystem audioSystem = new AudioSystem();
        DVDPlayer dvdPlayer = new DVDPlayer();
        GameConsole gameConsole = new GameConsole();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, audioSystem, dvdPlayer, gameConsole);

        homeTheater.watchMovie();
        homeTheater.adjustVolume(7); // Установка уровня громкости
        homeTheater.endMovie();

        homeTheater.playGame("Super Mario");

        homeTheater.listenToMusic();
        homeTheater.adjustVolume(5);

        homeTheater.turnOffSystem();
    }
}
