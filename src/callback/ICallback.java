package callback;

public interface ICallback<TResult> {
	void OnFinish(TResult result);
}
