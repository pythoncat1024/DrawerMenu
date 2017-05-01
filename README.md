# DrawerMenu

手写一个侧滑菜单是一种怎样的体验？

* android app 中有很多侧滑菜单的需求，于是谷歌推出了`DrawerLayout`控件。基本满足了用户的侧滑菜单需求。
* 但是，总有一些时候，需要去使用自定义的控件。
* 这个`DrawerMenu`就是在这种场景下出现的。首先，可以肯定的是，肯定没有原生的`DrawerLayout`好。但是，这个不重要的，重要的是，理清楚其中的逻辑。

	* 为什么可以侧滑？
	* 怎么做才能侧滑？
	* 如果实现侧滑，会不会影响菜单栏的点击事件？
	* 实现侧滑，有哪些注意事项？
* 我已经在网上看到过几十个人实现的侧滑菜单的讲解。优劣并不好区分。因为这个不重要。因为再好，也不会比原生的更好了。但是，造轮子的意义就在于，弄清楚其中的机理。
* 其实很多技术点，就是一张纸：捅破了，就明白了；不捅破，死也不能明白。
* 这篇侧滑菜单，就是为了捅捅。

**How to**
> To get a Git project into your build:

* Step 1. Add the JitPack repository to your build file
> Add it in your root build.gradle at the end of repositories:

	```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	```
* Step 2. Add the dependency

	```gradle
		dependencies {
	        compile 'com.github.pythoncat1024:DrawerMenu:1.0.0'
		}
	```
* Step 3. edit you Activity's xml
	```xml
	   <com.python.cat.drawerlayout.DrawerMenu
        android:id="@+id/dm_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

	        <include layout="@layout/left_menu_layout" />
	
	        <include layout="@layout/main_layout" />
	    </com.python.cat.drawerlayout.DrawerMenu>
	```
* Step 4. edit you Activity （*如果只是要看侧滑效果，可以没有这一步*）
```java
final DrawerMenu dm = (DrawerMenu) findViewById(R.id.dm_layout);
        findViewById(R.id.main_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.changeState();
            }
        });
        vp = (ViewPager) findViewById(R.id.main_view_pager);
        View v1 = LayoutInflater.from(this).inflate(R.layout.vp_layout_01, null);
        View v2 = LayoutInflater.from(this).inflate(R.layout.vp_layout_02, null);
        View v3 = LayoutInflater.from(this).inflate(R.layout.vp_layout_03, null);
        View v4 = LayoutInflater.from(this).inflate(R.layout.vp_layout_04, null);
        final ArrayList<View> viewList = new ArrayList<>();
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);
        viewList.add(v4);
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        });
    }

    public void leftMenuClick(View view) {
        String text = ((TextView) view).getText().toString();
        LogUtils.e("item = " + view.getId() + " , text = " + text);
        Toast.makeText(getApplicationContext(),
                text,
                Toast.LENGTH_SHORT)
                .show();
    }
```

具体实现：参考源码  [核心逻辑](https://github.com/pythoncat1024/DrawerMenu/blob/master/drawerlayout/src/main/java/com/python/cat/drawerlayout/DrawerMenu.java)
