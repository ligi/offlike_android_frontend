package org.rhok.offlike;

import android.os.Bundle;

public class PendingLikesActivity extends OfflikeFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list);
        this.del_pending();
        this.addPendingLike("title", "url");
        this.getSupportFragmentManager().beginTransaction().add(R.id.list_fragment, new PendingLikesListFragment(this.getPendingLikes(),this)).commit();
    }
}
