// Generated code from Butter Knife. Do not modify!
package com.example.greeklife.ui;

import android.view.View;
import butterknife.Views.Finder;

public class FraternityListView$$ViewInjector {
  public static void inject(Finder finder, final com.example.greeklife.ui.FraternityListView target, Object source) {
    View view;
    view = finder.findById(source, 2131099733);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099733' for field 'groupList' was not found. If this field binding is optional add '@Optional'.");
    }
    target.groupList = (android.widget.ListView) view;
  }

  public static void reset(com.example.greeklife.ui.FraternityListView target) {
    target.groupList = null;
  }
}
