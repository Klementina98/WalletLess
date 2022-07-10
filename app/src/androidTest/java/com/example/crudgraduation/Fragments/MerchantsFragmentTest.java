package com.example.crudgraduation.Fragments;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.crudgraduation.Adapter.CustomAdapter;
import com.example.crudgraduation.R;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MerchantsFragmentTest extends TestCase {

    @Rule
    public FragmentTestRule<MerchantsFragment> merchantsFragmentFragmentTestRule = new FragmentTestRule<>(MerchantsFragment.class);

    @Test
    public void testFragmentCanBeInstantiated() {

        // Launch the activity to make the fragment visible
        merchantsFragmentFragmentTestRule.launchActivity(null);

        // Then use Espresso to test the Fragment
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }
    @Test
    public void testAddMerchantFragmentOpened(){

        merchantsFragmentFragmentTestRule.launchActivity(null);
        onView(withId(R.id.add_merchant)).perform(click());
    }

    @Test
    public void testListMerchants(){

        merchantsFragmentFragmentTestRule.launchActivity(null);

        assertTrue(getRVcount()>0);
    }

    private int getRVcount(){
        RecyclerView recyclerView = (RecyclerView) merchantsFragmentFragmentTestRule.getActivity().findViewById(R.id.recyclerListCard);
        CustomAdapter customAdapter = (CustomAdapter) recyclerView.getAdapter();
        return customAdapter.getItemCount();
    }
    @Test
    public void testIfDakSportIsFirstInTheList(){
        merchantsFragmentFragmentTestRule.launchActivity(null);

        ;
    }






}