package cn.codebro.spider;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-03-19 09:18:04
 */
public interface DataTransfer {

    <T> boolean persistence(ParseResult<T> parseResult);

}
