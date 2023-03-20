package cn.codebro.spider;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-03-19 09:17:14
 */
public interface PageParser {

    <T> ParseResult<T> parse(Page page);

}
