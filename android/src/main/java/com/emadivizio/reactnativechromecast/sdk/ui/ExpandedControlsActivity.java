package com.emadivizio.reactnativechromecast.sdk.ui;

import android.view.Menu;

import com.emadivizio.reactnativechromecast.R;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.media.widget.ExpandedControllerActivity;

/**
 * Created by Emanuele on 19/09/2017.
 */

public class ExpandedControlsActivity extends ExpandedControllerActivity {

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.cast_activity_menu, menu);
    CastButtonFactory.setUpMediaRouteButton(this, menu, R.id.media_route_menu_item);
    return true;
  }
}
