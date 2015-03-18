package com.demo.UI.actionbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.R;
import com.demo.UI.actionbar.content.ContentItem;
import android.util.Log;

import java.util.ArrayList;
/**
 * Created by sifeier on 14-11-21.
 */
public class ActionBarShareActivity extends ActionBarActivity {

    private static final String TAG = "ActionBarShareActivity";

    // The items to be displayed in the ViewPager
    private final ArrayList<ContentItem> mItems = getSampleContent();

    // Keep reference to the ShareActionProvider from the menu
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view (which contains a CheeseListFragment)
        setContentView(R.layout.actionbar_share_main);

        // Retrieve the ViewPager from the content view
        ViewPager vp = (ViewPager) findViewById(R.id.viewpager);

        // Set an OnPageChangeListener so we are notified when a new item is selected
        vp.setOnPageChangeListener(mOnPageChangeListener);

        // Finally set the adapter so the ViewPager can display items
        vp.setAdapter(mPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu resource
        getMenuInflater().inflate(R.menu.actionbar_main, menu);

        // Retrieve the share menu item
        MenuItem shareItem = menu.findItem(R.id.menu_share);

        // Now get the ShareActionProvider from the item
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        return super.onCreateOptionsMenu(menu);
    }


    /**
     * A PagerAdapter which instantiates views based on the ContentItem's content type.
     */
    private final PagerAdapter mPagerAdapter = new PagerAdapter() {
        LayoutInflater mInflater;

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // Just remove the view from the ViewPager
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // Ensure that the LayoutInflater is instantiated
            if (mInflater == null) {
                mInflater = LayoutInflater.from(ActionBarShareActivity.this);
            }

            // Get the item for the requested position
            final ContentItem item = mItems.get(position);

            // The view we need to inflate changes based on the type of content
            switch (item.contentType) {
                case ContentItem.CONTENT_TYPE_TEXT: {
                    // Inflate item layout for text
                    TextView tv = (TextView) mInflater
                            .inflate(R.layout.item_text, container, false);

                    // Set text content using it's resource id
                    tv.setText(item.contentResourceId);

                    // Add the view to the ViewPager
                    container.addView(tv);
                    return tv;
                }
                case ContentItem.CONTENT_TYPE_IMAGE: {
                    // Inflate item layout for images
                    ImageView iv = (ImageView) mInflater
                            .inflate(R.layout.item_image, container, false);

                    // Load the image from it's content URI
                    iv.setImageURI(item.getContentUri());
                    Log.e(TAG, "image uri" + item.getContentUri());

                    // Add the view to the ViewPager
                    container.addView(iv);
                    return iv;
                }
            }

            return null;
        }
    };

    /**
     * A OnPageChangeListener used to update the ShareActionProvider's share intent when a new item
     * is selected in the ViewPager.
     */
    private final ViewPager.OnPageChangeListener mOnPageChangeListener
            = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.e(TAG, "position, positionOffset, positionOffsetPixels: " + position + " " + positionOffset + " " + positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {

            if (mShareActionProvider != null) {
                // Get the currently selected item, and retrieve it's share intent
                ContentItem item = mItems.get(position);
                Intent shareIntent = item.getShareIntent(ActionBarShareActivity.this);

                // Now update the ShareActionProvider with the new share intent
                mShareActionProvider.setShareIntent(shareIntent);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            // NO-OP
        }
    };

    /**
     * @return An ArrayList of ContentItem's to be displayed in this sample
     */
    static ArrayList<ContentItem> getSampleContent() {
        ArrayList<ContentItem> items = new ArrayList<>();

        items.add(new ContentItem(ContentItem.CONTENT_TYPE_IMAGE, "boom_p1.jpg"));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_TEXT, R.string.quote_1));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_TEXT, R.string.quote_2));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_IMAGE, "boom_p2.jpg"));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_TEXT, R.string.quote_3));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_IMAGE, "boom_p3.jpg"));

        return items;
    }
}
