package androidsamples.java.journalapp;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Instrumented tests for the {@link EntryDetailsFragment}.
 */
@RunWith(AndroidJUnit4.class)
public class EntryDetailsFragmentTest {
  @Test
  public void testNavigationToEntryListFragment() {
    // Create a TestNavHostController
    TestNavHostController navController = new TestNavHostController(
            ApplicationProvider.getApplicationContext());

    FragmentScenario<EntryListFragment> entryDetailsFragmentFragmentScenario
            = FragmentScenario.launchInContainer(EntryListFragment.class, null, R.style.Theme_JournalApp, (FragmentFactory) null);

    entryDetailsFragmentFragmentScenario.onFragment(fragment -> {
      // Set the graph on the TestNavHostController
      navController.setGraph(R.navigation.nav_graph);

      // Make the NavController available via the findNavController() APIs
      Navigation.setViewNavController(fragment.requireView(), navController);
    });

    // Verify that performing a click changes the NavController's state
    onView(ViewMatchers.withId(R.id.btn_add_entry)).perform(ViewActions.click());
    assertThat(Objects.requireNonNull(navController.getCurrentDestination()).getId(), is(R.id.entryDetailsFragment));
  }
}