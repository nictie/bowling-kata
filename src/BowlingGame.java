import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

public class BowlingGame implements ScoreKeeper {
    private final ScreenModel screenModel;
    private final Set<AbstractFrame> playedFrames;
    private final Rules rules;
    private AbstractFrame currentFrame;

    public BowlingGame(@NotNull ScreenModel screenModel, @NotNull Rules rules) {

        this.screenModel = screenModel;
        this.playedFrames = new HashSet<>();
        this.rules = rules;
    }

    public void roll(int hitPins) {

        if (currentFrame == null) {
            NullFrame previousFrame = new NullFrame();
            Frame firstFrame = new Frame(1, previousFrame, rules);
            playedFrames.add(firstFrame);
            currentFrame = firstFrame;
        }
        AbstractFrame nextFrame = currentFrame.roll(hitPins);
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

    public boolean isFinished() {

        return rules.forFrame(currentFrame).isLastFrame;
    }
}
