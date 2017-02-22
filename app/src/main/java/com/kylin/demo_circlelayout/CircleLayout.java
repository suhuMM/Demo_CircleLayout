package com.kylin.demo_circlelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CircleLayout extends ViewGroup{

	private float radius;//Բ�뾶
	private int mDegreeDelta; //�Ƕȼ��
	private int offset;//ƫ�ƽǶ�

	public CircleLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CircleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		TypedArray a = context.obtainStyledAttributes(attrs,  
				R.styleable.CircleLayout);
		//Բ�뾶
		radius = a.getDimension(R.styleable.CircleLayout_radius, 20);
		//ƫ�ƽǶ�
		offset = a.getInteger(R.styleable.CircleLayout_offset, 0);
		System.out.println("radius:"+radius);
		// TODO Auto-generated constructor stub
		a.recycle();
	}

	public CircleLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		//��ȡ��view����
		final int count = getChildCount();
		//���������view֮��ĽǶȲ�
		mDegreeDelta = 360/count;


		final int parentLeft = getPaddingLeft();
		final int parentRight = right - left - getPaddingRight();

		final int parentTop = getPaddingTop();
		final int parentBottom = bottom - top - getPaddingBottom();

		if (count < 1 ) {
			return;
		}
		System.out.println(Math.cos(0*Math.PI/180));
		for (int i = 0; i < count; i++) {

			final View child = getChildAt(i);
			if (child.getVisibility() != GONE) {

				final int width = child.getMeasuredWidth();
				final int height = child.getMeasuredHeight();

				int childLeft;
				int childTop;
				if (count == 1) {
					childLeft = parentLeft + (parentRight - parentLeft - width) / 2;
					childTop = parentTop + (parentBottom - parentTop - height) / 2 ;
					child.layout(childLeft, childTop,childLeft+width,childTop+height);

				}else{

					childLeft = (int) (parentLeft + (parentRight - parentLeft-width) / 2-(radius * Math.sin((i*mDegreeDelta+offset)*Math.PI/180)));
					childTop = (int) (parentTop + (parentBottom - parentTop-height) / 2-(radius * Math.cos((i*mDegreeDelta+offset)*Math.PI/180))) ;
					child.layout(childLeft, childTop,childLeft+width,childTop+height);

				}

			}
		}             

	}


	@Override  
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
	{  
		/** 
		 * ��ô�ViewGroup�ϼ�����Ϊ���Ƽ��Ŀ�͸ߣ��Լ�����ģʽ 
		 */  
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);  
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);  

		// ��������е�childView�Ŀ�͸�  
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(sizeWidth, sizeHeight);
	}


}
