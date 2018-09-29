package com.technocracy.app.aavartan.Sponsors.View;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Sponsors.Model.Data.Sponsor;
import com.technocracy.app.aavartan.Sponsors.Model.RetrofitSponsProvider;
import com.technocracy.app.aavartan.Sponsors.Presenter.SponsPresenter;
import com.technocracy.app.aavartan.Sponsors.Presenter.SponsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class SponsActivity extends AppCompatActivity implements SponsView {

    private ProgressBar progressBar;
    private RecyclerView mRecyclerView;
    private List<Sponsor>[] sponsorCategoryList;
    private SimpleAdapter mAdapter;
    private ArrayList<SectionedGridRecyclerViewAdapter.Section> sections;
    private SponsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sponsorCategoryList = new List[3];
        setContentView(R.layout.activity_sponsors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sponsors");
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_spons);
        mRecyclerView = (RecyclerView) findViewById(R.id.spons_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        presenter = new SponsPresenterImpl(new RetrofitSponsProvider(), this, this);
        presenter.getSpons();
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSpons(List<Sponsor> sponsorList) {
        List<Sponsor> spons2 = new ArrayList<>(), spons3 = new ArrayList<>(), spons4 = new ArrayList<>();
        for (int i = 0; i < sponsorList.size(); i++) {
            if (sponsorList.get(i).getType().equals("2"))
                spons2.add(sponsorList.get(i));
            else if (sponsorList.get(i).getType().equals("3"))
                spons3.add(sponsorList.get(i));
            else if (sponsorList.get(i).getType().equals("4"))
                spons4.add(sponsorList.get(i));
            sponsorCategoryList[0] = spons2;
            sponsorCategoryList[1] = spons3;
            sponsorCategoryList[2] = spons4;

            mAdapter = new SimpleAdapter(SponsActivity.this, sponsorCategoryList);
            sections = new ArrayList<SectionedGridRecyclerViewAdapter.Section>();
            sections.add(new SectionedGridRecyclerViewAdapter.Section(0, "MAJOR SPONSORS"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(sponsorCategoryList[0].size(), "EVENT SPONSORS"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(sponsorCategoryList[0].size() + sponsorCategoryList[1].size()
                    , "MEDIA PARTNERS"));
            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                    SectionedGridRecyclerViewAdapter(SponsActivity.this, R.layout.section, R.id.section_text, mRecyclerView, mAdapter);
            mSectionedAdapter.setSections(sections.toArray(dummy));
            mRecyclerView.setAdapter(mSectionedAdapter);
        }
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.spons_drawer_layout), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}