// Generated code from Butter Knife. Do not modify!
package com.example.greeklife.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class FraternityAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.example.greeklife.adapters.FraternityAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131099730);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099730' for field 'fratName' was not found. If this field binding is optional add '@Optional'.");
    }
    target.fratName = (android.widget.TextView) view;
    view = finder.findById(source, 2131099731);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099731' for field 'fratDescription' was not found. If this field binding is optional add '@Optional'.");
    }
    target.fratDescription = (android.widget.TextView) view;
  }

  public static void reset(com.example.greeklife.adapters.FraternityAdapter target) {
    target.fratName = null;
    target.fratDescription = null;
  }
}
