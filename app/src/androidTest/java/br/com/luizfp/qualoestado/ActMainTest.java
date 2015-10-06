package br.com.luizfp.qualoestado;

import android.support.test.espresso.matcher.PreferenceMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import br.com.luizfp.qualoestado.activities.ActMain;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by luiz on 10/5/15.
 */
public class ActMainTest {

    private static final String TOGGLE_BUTTON_SOUND = "toggle_button_sound";
    private static final String TOGGLE_BG_MUSIC = "toggle_bg_music";
    private static final String LIST_BG_MUSIC = "list_bg_music";

    @Rule
    public ActivityTestRule<ActMain> mActivityRule =
            new ActivityTestRule<>(ActMain.class);

    @Test
    public void testNewGameButton() {
        onView(withId(R.id.btnNovoJogo))
                .perform(click());
        onView(withId(R.id.btnConfirmarResposta))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testStatsButton() {
        onView(withId(R.id.btnEstatisticas))
                .perform(click());
        onView(withId(R.id.txtStats))
                .check(matches(isDisplayed()));
    }

}
