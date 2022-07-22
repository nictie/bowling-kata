import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

public class BowlingGame implements ScoreKeeper {
    private final ScreenModel screenModel;
    private final Set<AbstractFrame> playedFrames;
    private AbstractFrame currentFrame;

    public BowlingGame(@NotNull ScreenModel screenModel) {

        this.screenModel = screenModel;
        this.playedFrames = new HashSet<>();
    }

    public void roll(int hitPins) {

        if (currentFrame == null) {
            Frame nextFrame = new Frame(1, new NullFrame());
            playedFrames.add(nextFrame);
            currentFrame = nextFrame;
        }
        AbstractFrame nextFrame = currentFrame.addRoll(hitPins);
        if (playedFrames.add(nextFrame)) {
            currentFrame = nextFrame;
        }
        screenModel.updateModel(this);
    }

    @Override
    public void writeTo(ScreenModelImpl screenModel) {

        playedFrames.forEach(frame -> frame.writeTo(screenModel));
    }

    @Override
    public String toString() {

        return "BowlingGame{" +
                "screenModel=" + screenModel +
                ", playedFrames=" + playedFrames +
                ", currentFrame=" + currentFrame +
                '}';
    }
}
